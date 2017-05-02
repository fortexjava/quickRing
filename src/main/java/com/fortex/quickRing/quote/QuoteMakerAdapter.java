package com.fortex.quickRing.quote;

import static com.fortex.lib.globalservices.FortexLogger.defaultLogger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fortex.lib.protocol.FIX_DEF;
import com.fortex.lib.protocol.FortexJSONMsg;
import com.fortex.quickRing.model.FxTableModel;
import com.fortex.quickRing.model.QuoteOffsetModel;
import com.fortex.quickRing.utils.DoubleUtil;

import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.Message;
import quickfix.field.MDEntryPx;
import quickfix.field.MDEntrySize;
import quickfix.field.MDEntryType;
import quickfix.fix44.MarketDataSnapshotFullRefresh;

public class QuoteMakerAdapter {
	protected final ConcurrentMap<String, Map<Integer, QuoteMaker>> mapAllSymQm;
	private Map<String, FxTableModel> decimalMapping;

	public static class QuoteMaker {
		public static class CAT {
			public static final int FIXED_SPREAD = 1, ADJUSTED_SPREAD = 2, POSTBIDASK_SPREAD = 3, DEFAULT_RESET = 4;
		};

		public static final int VAL_BID = 0, VAL_ASK = 1;
		public static final double NA_SPREAD = -10000;

		public String strSym;
		public int nDomain, nCategory, nL2MarkupSteps;
		public double dBid, dAsk, dSpread, dMinSpread, dMaxSpread, dL2Markup;

		public boolean isToRemoveQM() {
			return (dBid == 0 && dAsk == 0 && dSpread == 0 && dMinSpread <= NA_SPREAD && dMaxSpread == 0);
		}

		static int TXT2CAT(String category) {
			if (category.equals("S") || category.equals("s"))
				return CAT.FIXED_SPREAD;
			else if (category.equals("O") || category.equals("o"))
				return CAT.ADJUSTED_SPREAD;
			else if (category.equals("P") || category.equals("p"))
				return CAT.POSTBIDASK_SPREAD;
			else if (category.equals("R"))
				return CAT.DEFAULT_RESET;
			else
				return 0;
		};

		public void SetFxjValue(JSONObject jsVal) {
			strSym = FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.Symbol, "");
			nDomain = FortexJSONMsg.optInt(jsVal, FIX_DEF.TAGS.Fortex_Domain, -1);
			nCategory = TXT2CAT(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.Fortex_QM_SubType, ""));
			dBid = Double.parseDouble(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.BidPx, ""));
			dAsk = Double.parseDouble(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.OfferPx, ""));
			dSpread = Double.parseDouble(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.Spread, ""));
			dMinSpread = Double.parseDouble(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.Fortex_MinSpread, ""));
			dMaxSpread = Double.parseDouble(FortexJSONMsg.optString(jsVal, FIX_DEF.TAGS.Fortex_MaxSpread, ""));

			// nL2MarkupSteps = FortexJSONMsg.optInt(jsVal,
			// FIX_DEF.TAGS.Fortex_Domain, -1);
			// dL2Markup = Double.parseDouble(FortexJSONMsg.optString(jsVal,
			// FIX_DEF.TAGS.Fortex_MaxSpread, ""));
		}
	}

	public QuoteMakerAdapter() {
		mapAllSymQm = new ConcurrentHashMap<>();
	}

	public void onQmUpdate(JSONObject jsQm) {

		try {
			defaultLogger.info("#start to update qm" + jsQm.toString());
			QuoteMaker qm = new QuoteMaker();
			qm.SetFxjValue(jsQm);
			
			// update in-memory QM cache
			Map<Integer, QuoteMaker> mapSymQm = mapAllSymQm.get(qm.strSym);
			boolean isToRemove = qm.isToRemoveQM();
			if (mapSymQm == null && !isToRemove) {
				mapSymQm = new ConcurrentHashMap<Integer, QuoteMaker>();
				mapAllSymQm.put(qm.strSym, mapSymQm);
			}

			if (qm.nDomain == 0) {
				if (mapSymQm != null) {
					mapSymQm.clear(); // domain0 QM will overwrite all
										// sub-domains' QM
				}
				if (!isToRemove) {
					mapSymQm.put(0, qm);
				}
			} else {
				if (isToRemove) {
					mapSymQm.remove(qm.nDomain);
					defaultLogger.info("#remove qm" + jsQm.toString());
				} else {
					mapSymQm.put(qm.nDomain, qm);
				}
			}

			if (isToRemove && mapSymQm != null && mapSymQm.isEmpty()) {
				mapAllSymQm.remove(qm.strSym);
			}

		} catch (Exception e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}
	}

	public Map<Integer, QuoteMaker> getQmBySym(String sym) {
		return mapAllSymQm.get(sym);
	}

	public static QuoteMaker getQM(Map<Integer, QuoteMakerAdapter.QuoteMaker> dmnQMs, String dmn) {
		if (dmnQMs == null) {
			return null;
		}

		QuoteMaker qm = dmnQMs.get(Integer.parseInt(dmn));
		if (qm == null) {
			qm = dmnQMs.get(0);
		}

		return qm;
	}

	private QuoteOffsetModel getDomainizedL1Px(QuoteOffsetModel quotePrice, QuoteMaker qm, int scale) {
		QuoteOffsetModel offset = new QuoteOffsetModel(quotePrice.getBid(), quotePrice.getAsk());
		if (quotePrice.getBid() > 0 && quotePrice.getAsk() > 0) {
			switch (qm.nCategory) {
			case QuoteMaker.CAT.ADJUSTED_SPREAD: {
				offset.setBid(DoubleUtil.add(quotePrice.getBid(), qm.dBid, scale));
				offset.setAsk(DoubleUtil.add(quotePrice.getAsk(), qm.dAsk, scale));
				double dTempSpread = DoubleUtil.sub(quotePrice.getAsk(), quotePrice.getBid(), scale);
				double dConstSpread = QuoteMaker.NA_SPREAD;
				if (qm.dMinSpread != QuoteMaker.NA_SPREAD && dTempSpread < qm.dMinSpread) {
					dConstSpread = qm.dMinSpread;
				}
				if (qm.dMaxSpread > 0 && dTempSpread > qm.dMaxSpread) {// max
																		// spread
																		// enabled
																		// and
																		// triggered
					dConstSpread = qm.dMaxSpread;
				}
				if (dConstSpread != QuoteMaker.NA_SPREAD) {
					offset.setBid(((new BigDecimal(quotePrice.getBid())
							.add(new BigDecimal(quotePrice.getAsk()).setScale(scale, BigDecimal.ROUND_HALF_UP)
									.subtract(new BigDecimal(dConstSpread)).setScale(scale, BigDecimal.ROUND_HALF_UP)))
											.divide(new BigDecimal(2), scale, BigDecimal.ROUND_HALF_UP)).doubleValue());
					offset.setAsk(DoubleUtil.add(quotePrice.getAsk(), dConstSpread, scale));
				}
				break;
			}

			case QuoteMaker.CAT.FIXED_SPREAD: {
				offset.setBid(((new BigDecimal(quotePrice.getBid())
						.add(new BigDecimal(quotePrice.getAsk()).setScale(scale, BigDecimal.ROUND_HALF_UP)
								.subtract(new BigDecimal(qm.dSpread)).setScale(scale, BigDecimal.ROUND_HALF_UP)))
										.divide(new BigDecimal(2), scale, BigDecimal.ROUND_HALF_UP)).doubleValue());
				offset.setAsk(DoubleUtil.add(offset.getBid(), qm.dSpread, scale));
				break;
			}

			case QuoteMaker.CAT.POSTBIDASK_SPREAD: {
				offset.setBid(qm.dBid);
				offset.setAsk(qm.dAsk);
				break;
			}
			}
		}
		return offset;
	}
	
	public Group getDomainizedL2Px(QuoteOffsetModel first, Group g, QuoteMaker qm, String symbol) throws FieldNotFound {
		if (qm != null && this.decimalMapping.get(qm.strSym) != null) {
			int scale = this.decimalMapping.get(qm.strSym).getDecimal();
			QuoteOffsetModel newOffset = getDomainizedL1Px(first, qm, scale);
			newOffset.setBid(DoubleUtil.sub(newOffset.getBid(), first.getBid(), scale));
			newOffset.setAsk(DoubleUtil.sub(newOffset.getAsk(), first.getAsk(), scale));
			if (g.getChar(MDEntryType.FIELD) == MDEntryType.BID) {
				g.setDouble(MDEntryPx.FIELD,
						DoubleUtil.add(g.getDouble(MDEntryPx.FIELD), newOffset.getBid(), scale));
			} else if (g.getChar(MDEntryType.FIELD) == MDEntryType.OFFER) {
				g.setDouble(MDEntryPx.FIELD,
						DoubleUtil.add(g.getDouble(MDEntryPx.FIELD), newOffset.getAsk(), scale));
			}
			
		}
		return g;
	}
	
	public void setDecimalMapping(Map<String, FxTableModel> decimalMapping) {
		this.decimalMapping = decimalMapping;
	}

	public QuoteOffsetModel getFirst(List<Group> list) throws FieldNotFound {
		QuoteOffsetModel quotePrice = new QuoteOffsetModel();
		boolean isBidSet = false, isOfferSet = false;
		for (Group g : list) {
			if (!isBidSet && g.isSetField(MDEntryType.FIELD) && g.getChar(MDEntryType.FIELD) == MDEntryType.BID) {
				quotePrice.setBid(g.getDouble(MDEntryPx.FIELD));
				isBidSet = true;
			} else if (!isOfferSet && g.isSetField(MDEntryType.FIELD)
					&& g.getChar(MDEntryType.FIELD) == MDEntryType.OFFER) {
				quotePrice.setAsk(g.getDouble(MDEntryPx.FIELD));
				isOfferSet = true;
			}
			if (isBidSet && isOfferSet)
				break;
		}
		return quotePrice;
	}
	
	public Message calculate(String symbol, Integer domain,  List<Group> groups, Message msg, int depth) throws FieldNotFound {
		Map<Integer, QuoteMaker> domainMap = this.getQmBySym(symbol);
		int cBid = 0, cOffer = 0;
		QuoteOffsetModel first = this.getFirst(groups);
		
		for (Group g : groups) {
			if (g.getChar(MDEntryType.FIELD) == MDEntryType.BID && ++cBid <= depth
					|| g.getChar(MDEntryType.FIELD) == MDEntryType.OFFER && ++cOffer <= depth) {
				Group group = new MarketDataSnapshotFullRefresh.NoMDEntries();
				group.setChar(MDEntryType.FIELD, g.getChar(MDEntryType.FIELD));
				group.setString(MDEntryPx.FIELD, g.getString(MDEntryPx.FIELD));
				group.setString(MDEntrySize.FIELD, g.getString(MDEntrySize.FIELD));
				
				
				if (domainMap != null && domain != null) {
					QuoteMaker qm = domainMap.get(domain);
					if(qm == null)
						qm = domainMap.get(0);
					if (qm != null) {
						try {
							group = this.getDomainizedL2Px(first, group, qm, symbol);
						} catch (FieldNotFound e) {
							Logger.getLogger("EventError").error(e.getMessage(), e);
						}
					}
				}
				msg.addGroup(group);
			}
		}
		return msg;
	}

}
