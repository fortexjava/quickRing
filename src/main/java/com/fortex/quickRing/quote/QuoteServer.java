package com.fortex.quickRing.quote;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fortex.quickRing.AbstractServer;
import com.fortex.quickRing.Statistics.QuoteStatistician;
import com.fortex.quickRing.cache.SessionCache;
import com.fortex.quickRing.utils.CSVUtil;

import quickfix.Application;
import quickfix.SessionSettings;


public class QuoteServer extends AbstractServer{

	private static final CountDownLatch shutdownLatch = new CountDownLatch(1);
	private static final QuoteMakerAdapter qmAdpt = new QuoteMakerAdapter();
	
	public QuoteServer(String initiatorFilePath, String acceptorFilePath) throws Exception {
		super(initiatorFilePath, acceptorFilePath);
		
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					CSVUtil.writeXRingStat(new String[]{"", "OnlineUsers","MD_RecvTotal","MD_SendTotal","MD_DropRatio","MD_ProcTimeAvg",
							"MD_ProcTimeMax","MD_ProcTimeMin"},parse(), "quote");
				} catch (IOException e) {
					Logger.getLogger("EventError").error(e);
				}
				QuoteStatistician.getInstance().clear();
				//quoteStatistician.clear();
				//tradeStatistician.clear();
			}
		},  1, 1, TimeUnit.MINUTES);
	}
	
	private static String parse() {
		QuoteStatistician quoteStatistician = QuoteStatistician.getInstance();
		StringBuffer sb = new StringBuffer(200);
		sb.append(parseCurrentDate()).append(",");
		//Quote
		//sb.append(onlineUsers).append(",")
				sb.append(SessionCache.getUserCount()).append(",")
				.append(quoteStatistician.getTotalReceived()).append(",")
				.append(quoteStatistician.getTotalSended()).append(",")
				.append(quoteStatistician.getDropRatio()).append(",")
				.append(quoteStatistician.getProccessingTimeAvg()).append(",")
				.append(quoteStatistician.getProccessingTimeMax()).append(",")
				.append(quoteStatistician.getProccessingTimeMin()).append(",");
		return sb.toString();
	} 

	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.AbstractServer#getInitiatorApplication()
	 */
	@Override
	protected Application getInitiatorApplication(SessionSettings settings) {
		FQSAdaptor fqsAdaptor = new FQSAdaptor();
		QuoteInitiatorApplication application = new QuoteInitiatorApplication(settings, qmAdpt);
		application.setFqsAdaptor(fqsAdaptor);
		fqsAdaptor.setQuoteInitiatorApp(application);
		/** Init FQS **/
		fqsAdaptor.init(); 
		return application;
	}

	/*
	 * (non-Javadoc)
	 * @see com.fortex.quickRing.AbstractServer#getAcceptorApplication()
	 */
	@Override
	protected Application getAcceptorApplication(SessionSettings settings) {
		return new QuoteAcceptorApplication(qmAdpt);
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
			Logger.getLogger("EventError").error(e.getMessage(),e);
		}
	}

}
