package com.fortex.quickRing.trade;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fortex.quickRing.AbstractServer;
import com.fortex.quickRing.Statistics.TradeStatistician;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.utils.CSVUtil;

import quickfix.Application;
import quickfix.SessionSettings;


public class TradeServer extends AbstractServer{
 	private static final CountDownLatch shutdownLatch = new CountDownLatch(1);
	
	public TradeServer(String initiatorFilePath, String acceptorFilePath) throws Exception {
		super(initiatorFilePath, acceptorFilePath);
		
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				
				try {
					CSVUtil.writeXRingStat(new String[]{"", "OnlineUsers", "Ord_RecvTotal","Ord_RoutedTotal","Ord_RouteRespTotal",
							"Ord_SendToClientTotal","Ord_RouteRej","Ord_RouteFill","Ord_ProcTimeAvg", "Ord_ProcTimeMax",
							"Ord_ProcTimeMin","Ord_RespProcTimeAvg","Ord_RespProcTimeMax","Ord_RespProcTimeMin"}, parse(), "trade");
				} catch (IOException e) {
					Logger.getLogger("EventError").error(e);
				}
				
				TradeStatistician.getInstance().clear();
			}
		},  1, 1, TimeUnit.MINUTES);
		
	}
	
	/**
	 * 
	 * <p>Description:</p> 
	 *
	 * @author Patrick Chi
	 * @date 2017-03-27 
	 * @return String
	 * 
	 */
	private static String parse() {
		TradeStatistician tradeStatistician = TradeStatistician.getInstance();
		StringBuffer sb = new StringBuffer(200);
		sb.append(parseCurrentDate()).append(",");
		//Trade
		sb.append(SessionCache.getUserCount()).append(",")
		.append(tradeStatistician.getOrderReceivedTotal()).append(",")
		.append(tradeStatistician.getOrderRoutedTotal()).append(",")
		.append(tradeStatistician.getOrderRouteRespTotal()).append(",")
		.append(tradeStatistician.getOrderSendToClientTotal()).append(",")
		.append(tradeStatistician.getOrderRouteReject()).append(",")
		.append(tradeStatistician.getOrderRouteFill()).append(",")
		.append(tradeStatistician.getOrderRouteProccessingTimeAvg()).append(",")
		.append(tradeStatistician.getOrderRouteProccessingTimeMax()).append(",")
		.append(tradeStatistician.getOrderRouteProccessingTimeMin()).append(",")
		.append(tradeStatistician.getOrderRouteRespProccessingTimeAvg()).append(",")
		.append(tradeStatistician.getOrderRouteRespProccessingTimeMax()).append(",")
		.append(tradeStatistician.getOrderRouteRespProccessingTimeMin());
		
		return sb.toString();
	} 

	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.AbstractServer#getInitiatorApplication()
	 */
	@Override
	protected Application getInitiatorApplication(SessionSettings settings) {
		return new TradeInitiatorApplication(settings);
	}


	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.AbstractServer#getAcceptorApplication()
	 */
	@Override
	protected Application getAcceptorApplication(SessionSettings settings) {
		return new TradeAcceptorApplication(settings);
	}

	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.AbstractServer#doExecute()
	 */
	@Override
	protected void doExecute() {
		try {
			this.initiator.start();
			this.acceptor.start();
			shutdownLatch.await();
		} catch(Exception e) {
			Logger.getLogger("EventError").error(e.getMessage(), e);
		}
	}

}
