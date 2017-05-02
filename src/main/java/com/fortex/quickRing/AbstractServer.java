package com.fortex.quickRing;

import static quickfix.Acceptor.SETTING_ACCEPTOR_TEMPLATE;
import static quickfix.Acceptor.SETTING_SOCKET_ACCEPT_ADDRESS;
import static quickfix.Acceptor.SETTING_SOCKET_ACCEPT_PORT;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.fortex.log.FixServerLogFactory;
import com.fortex.quickRing.cache.TradeCache;
import com.fortex.quickRing.handler.MessageHandler;
import com.fortex.quickRing.utils.ConfigSetting;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FieldConvertError;
import quickfix.FileStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RingBufferSocketAcceptor;
import quickfix.RingBufferSocketInitiator;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;
import quickfix.SocketInitiator;
import quickfix.mina.acceptor.AbstractSocketAcceptor;
import quickfix.mina.acceptor.DynamicAcceptorSessionProvider;
import quickfix.mina.acceptor.DynamicAcceptorSessionProvider.TemplateMapping;
import quickfix.mina.initiator.AbstractSocketInitiator;

import com.lmax.disruptor.WaitStrategy;

public abstract class AbstractServer {
	
	private static final String KEY_PENDING_ORDER_COUNT = "PendingOrderCount";
	private static enum CONNECTION_TYPE{INITIATOR,ACCEPTOR};
	private final Map<InetSocketAddress, List<TemplateMapping>> dynamicSessionMappings = new HashMap<InetSocketAddress, List<TemplateMapping>>();
	protected AbstractSocketAcceptor acceptor;
	protected ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	protected AbstractSocketInitiator initiator;
	public AbstractServer(String initiatorFilePath, String acceptorFilePath) throws Exception{
		this.initialize(initiatorFilePath, CONNECTION_TYPE.INITIATOR);
		this.initialize(acceptorFilePath, CONNECTION_TYPE.ACCEPTOR);
	}
	
	protected static String parseCurrentDate() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		String result = formater.format(c.getTime());
		return result;
	}
	/**
	 * 
	 * <p>Description:Initialize the acceptor and initiator.</p> 
	 *
	 * @author Patrick Chi
	 * @date 2016-07-13 
	 * @param confFilePath:Path of configuration file for initiator and acceptor.
	 * @param serverType:Specify the server is initiator or acceptor.
	 * @throws Exception
	 */
	private void initialize(String confFilePath,  CONNECTION_TYPE serverType) throws Exception{
		SessionSettings settings = new SessionSettings(new FileInputStream(confFilePath));
		LogFactory logFactory = new FixServerLogFactory(settings);
		
		boolean acceptorUseRingBuffer = Boolean.valueOf(ConfigSetting.getServerProperty("acceptorUseRingBuffer"));	
		boolean initiatorUseRingBuffer = Boolean.valueOf(ConfigSetting.getServerProperty("initiatorUseRingBuffer"));
		final int buffersize = Integer.parseInt(ConfigSetting.getServerProperty("bufferSize"));
		String waitStrategyName = ConfigSetting.getServerProperty("waitStrategy");
		WaitStrategy waitStrategy = (WaitStrategy)Class.forName(waitStrategyName).newInstance();
		if (settings.isSetting(KEY_PENDING_ORDER_COUNT))
			TradeCache.setPendingOrderCount(settings.getLong(KEY_PENDING_ORDER_COUNT));
		
		if (CONNECTION_TYPE.ACCEPTOR.equals(serverType)) {
			MessageFactory messageFactory = new DefaultMessageFactory();
			MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
			if(acceptorUseRingBuffer)
				acceptor = new RingBufferSocketAcceptor(getAcceptorApplication(settings), new FileStoreFactory(settings), settings,  logFactory, new DefaultMessageFactory(), new MessageHandler[]{new MessageHandler()}, buffersize,waitStrategy);
			else
				acceptor = new SocketAcceptor(getAcceptorApplication(settings), messageStoreFactory, settings, logFactory, messageFactory);		
			configureDynamicSessions(settings, getAcceptorApplication(settings), messageStoreFactory, logFactory, messageFactory);
		} else {
			boolean isFTS = settings.isSetting("FTSUserName");
	        String userName = null;
	        String key = null;
	        String fileName = null;
	        if (isFTS) {
	        	userName = settings.getString("FTSUserName");
	        	key = "TradeStatus";
	        	fileName = ConfigSetting.getLocPath() + "tradeConnection.status";
	        } else {
	        	userName = settings.getString("FQSUserName");
	        	key = "QuoteStatus";
	        	fileName = ConfigSetting.getLocPath() + "quoteConnection.status";
	        }
	        SessionID sessionID = settings.sectionIterator().next();
	        String ip = settings.getString(sessionID, "SocketConnectHost");
			MessageFactory messageFactory = new DefaultMessageFactory();
			MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
			if(initiatorUseRingBuffer)
				initiator = new RingBufferSocketInitiator(getInitiatorApplication(settings), messageStoreFactory, settings, logFactory,messageFactory,  new MessageHandler[]{new MessageHandler()}, buffersize,waitStrategy, ip, userName, key, fileName);
			else
				initiator = new SocketInitiator(getInitiatorApplication(settings), messageStoreFactory, settings, logFactory, messageFactory, ip, userName, key, fileName);			
		}
		
	}
	
	private void configureDynamicSessions(SessionSettings settings, Application application,
            MessageStoreFactory messageStoreFactory, LogFactory logFactory,
            MessageFactory messageFactory) throws ConfigError, FieldConvertError {
        Iterator<SessionID> sectionIterator = settings.sectionIterator();
        while (sectionIterator.hasNext()) {
            SessionID sessionID = sectionIterator.next();
            if (isSessionTemplate(settings, sessionID)) {
                InetSocketAddress address = getAcceptorSocketAddress(settings, sessionID);
                getMappings(address).add(new TemplateMapping(sessionID, sessionID));
            }
        }

        for (Map.Entry<InetSocketAddress, List<TemplateMapping>> entry : dynamicSessionMappings
                .entrySet()) {
            acceptor.setSessionProvider(entry.getKey(), new DynamicAcceptorSessionProvider(
                    settings, entry.getValue(), application, messageStoreFactory, logFactory,
                    messageFactory));
        }
    }

    private List<TemplateMapping> getMappings(InetSocketAddress address) {
        List<TemplateMapping> mappings = dynamicSessionMappings.get(address);
        if (mappings == null) {
            mappings = new ArrayList<TemplateMapping>();
            dynamicSessionMappings.put(address, mappings);
        }
        return mappings;
    }

    private InetSocketAddress getAcceptorSocketAddress(SessionSettings settings, SessionID sessionID)
            throws ConfigError, FieldConvertError {
        String acceptorHost = "0.0.0.0";
        if (settings.isSetting(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS)) {
            acceptorHost = settings.getString(sessionID, SETTING_SOCKET_ACCEPT_ADDRESS);
        }
        int acceptorPort = (int) settings.getLong(sessionID, SETTING_SOCKET_ACCEPT_PORT);

        return new InetSocketAddress(acceptorHost, acceptorPort);
    }

    private boolean isSessionTemplate(SessionSettings settings, SessionID sessionID)
            throws ConfigError, FieldConvertError {
        return settings.isSetting(sessionID, SETTING_ACCEPTOR_TEMPLATE)
                && settings.getBool(sessionID, SETTING_ACCEPTOR_TEMPLATE);
    }

	

	/**
	 * 
	 * <p>Description:Get instance of Application for initiator</p> 
	 *
	 * @author Patrick Chi
	 * @date 2016-07-13 
	 * 
	 */
	protected abstract Application getInitiatorApplication(SessionSettings settings);
	
	/**
	 * 
	 * <p>Description:Get instance of Application for acceptor</p> 
	 *
	 * @author Patrick Chi
	 * @date 2016-07-13 
	 * @return
	 */
	protected abstract Application getAcceptorApplication(SessionSettings settings);
	
	 
	/**
	 * 
	 * <p>Description:execute to start the server</p> 
	 *
	 * @author Patrick Chi
	 * @date 2016-07-13
	 */
	protected abstract void doExecute();
}
