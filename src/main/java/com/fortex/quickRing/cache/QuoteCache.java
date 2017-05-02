package com.fortex.quickRing.cache;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.mina.util.ConcurrentHashSet;

import com.fortex.quickRing.model.SessionIDMarketDepthModel;

import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.SessionID;


public class QuoteCache {
	private static Map<String, List<Group>> SYMBOL_MARKET_DATA = 
			new ConcurrentHashMap<String, List<Group>>();
	
	private static Map<String, ConcurrentSkipListSet<SessionIDMarketDepthModel>> SYMBOL_SESSIONID_DATA =
			new ConcurrentHashMap<String, ConcurrentSkipListSet<SessionIDMarketDepthModel>>();
	
	private static Map<String, Long> QUOTE_TIME_MAPPING = new ConcurrentHashMap<String, Long>();
	
	private static Set<String> SUPPORTED_SYMBOLS = new ConcurrentHashSet<String>();
	
	public static void addMarketData(String symbol, List<Group> groups) {
		SYMBOL_MARKET_DATA.put(symbol, groups);
	}
	
	public static List<Group> getMarketDataBySymble(String symbol) {
		return SYMBOL_MARKET_DATA.get(symbol);
	}
	
	public static String[] getAllSymbols() {
		return SYMBOL_SESSIONID_DATA.keySet().toArray(new String[]{});
	}
	
	
	
	public static ConcurrentSkipListSet<SessionIDMarketDepthModel> getSessionIDsBySymbol(String symbol) {
		return SYMBOL_SESSIONID_DATA.get(symbol);
	}
	
	
	public static void addASessionIDToSymbolSessionID(String symbol, SessionIDMarketDepthModel sessionIDMarketDepth) {
		ConcurrentSkipListSet<SessionIDMarketDepthModel> list = getSessionIDsBySymbol(symbol);
		if(list == null || list.size() == 0) {
			list = new ConcurrentSkipListSet<SessionIDMarketDepthModel>(new Comparator<SessionIDMarketDepthModel>() {
				@Override
				public int compare(SessionIDMarketDepthModel o1, SessionIDMarketDepthModel o2) {
					return o1.getSessionID().hashCode() - o2.getSessionID().hashCode();
				}
			});
			SYMBOL_SESSIONID_DATA.put(symbol, list);
		}
		list.add(sessionIDMarketDepth);
		
	}
	
	/**
	 * <p>Description:Remove SessionID from SymbolSessionID cache</p>
	 * @author Ivan Huo
	 * @date 2016-09-03	
	 * @param symbol
	 * @param sessionID
	 */
	public static void removeSessionIDFromSymbolSessionID(String symbol,SessionID sessionID){
		ConcurrentSkipListSet<SessionIDMarketDepthModel> list = getSessionIDsBySymbol(symbol);
		if(list != null && list.size()>0){
			list.remove(new SessionIDMarketDepthModel(sessionID, 0, null));
		}
	}
	
	public static void removeSessionIDForSymbol(SessionID sessionID) {
		for (String key : SYMBOL_SESSIONID_DATA.keySet()) {
			ConcurrentSkipListSet<SessionIDMarketDepthModel> list =  getSessionIDsBySymbol(key);
			if(list != null && list.size() != 0) {
				for (SessionIDMarketDepthModel s : list)
					if(s.getSessionID().equals(sessionID))
						list.remove(s);
			}
		}
		
	}
	
	public static void cacheMarketDataGroup(String symbol, List<Group> groups) throws FieldNotFound {
		addMarketData(symbol, groups);
	}
	
	public static void addSupportedSymbol(String symbol) {
		SUPPORTED_SYMBOLS.add(symbol);
	}
	
	public static boolean isSupportedSymbol(String symbol) {
		return SUPPORTED_SYMBOLS.contains(symbol);
	}
	
	public static void putSymbolEncodedTime(String symbol, long time) {
		QUOTE_TIME_MAPPING.put(symbol, time);
	}
	
	public static long getSymbolEncodedTime(String symbol) {
		return QUOTE_TIME_MAPPING.get(symbol);
	} 
	
}
