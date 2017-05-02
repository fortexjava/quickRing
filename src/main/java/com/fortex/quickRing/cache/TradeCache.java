package com.fortex.quickRing.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.fortex.quickRing.model.SessionResponsedModel;

import quickfix.SessionID;

public class TradeCache {
	private static final Map<String, SessionResponsedModel> ORDER_ID_RESPONSE_CACHE = new ConcurrentHashMap<>();
	private static final Map<Integer, SessionResponsedModel> SEQNO_RESPONSE_CACHE = new ConcurrentHashMap<>();
	private static final Map<SessionID, Integer> PENDING_COUNT_CACHE = new ConcurrentHashMap<>();
	private static long PENDING_ORDER_PER_SESSION = 0;
	private static long PENDING_ORDER_COUNT = 1;
	
	public static void putModelWithOrderId(String orderId, SessionResponsedModel model) {
		ORDER_ID_RESPONSE_CACHE.put(orderId, model);
	}
	
	public static SessionResponsedModel getModelByOrderId(String orderId) {
		return ORDER_ID_RESPONSE_CACHE.get(orderId);
	}
	
	public static void putModelWithSeqNo(Integer seqNo, SessionResponsedModel model) {
		SEQNO_RESPONSE_CACHE.put(seqNo, model);
	}
	
	public static SessionResponsedModel getModelBySeqNo(Integer seqNo) {
		return SEQNO_RESPONSE_CACHE.get(seqNo);
	}
	
	public static void cleanMdReqIdSessionIdByMdReqId(long cleanTime){
		
		Iterator<Entry<String, SessionResponsedModel>> orderIdIt = ORDER_ID_RESPONSE_CACHE.entrySet().iterator();
		while (orderIdIt.hasNext()) {
			Entry<String, SessionResponsedModel> entry = orderIdIt.next();
			SessionResponsedModel sessionResponsedModel = entry.getValue();
			long distanceTime = System.currentTimeMillis() - sessionResponsedModel.getGeneratedTime();
			if (sessionResponsedModel.isResponsed() && distanceTime > cleanTime)
				orderIdIt.remove();
		}
		
		Iterator<Entry<Integer, SessionResponsedModel>> seqNoIt = SEQNO_RESPONSE_CACHE.entrySet().iterator();
		while (seqNoIt.hasNext()) {
			Entry<Integer, SessionResponsedModel> entry = seqNoIt.next();
			SessionResponsedModel sessionResponsedModel = entry.getValue();
			long distanceTime = System.currentTimeMillis() - sessionResponsedModel.getGeneratedTime();
			if (sessionResponsedModel.isResponsed() && distanceTime > cleanTime)
				seqNoIt.remove();
		}
		
	}
	
	public static void cleanSeqNumCache() {
		ORDER_ID_RESPONSE_CACHE.clear();
	}
	
	public static void initPendingCount(SessionID sessionId) {
		PENDING_COUNT_CACHE.put(sessionId, 0);
	} 
	
	public static void reducePendingCount(SessionID sessionId) {
		PENDING_COUNT_CACHE.put(sessionId, getPendingCount(sessionId) - 1);
	}
	
	public static void addPendingCount(SessionID sessionId) {
		PENDING_COUNT_CACHE.put(sessionId, getPendingCount(sessionId) + 1);
	}
	
	public static int getPendingCount(SessionID sessionId) {
		int count = 0;
		if (PENDING_COUNT_CACHE.containsKey(sessionId))
			count =  PENDING_COUNT_CACHE.get(sessionId);
		return count;
	}
	
	public static void resetAllPendingCount() {
		for (Entry<SessionID, Integer> entry : PENDING_COUNT_CACHE.entrySet()) {
			entry.setValue(0);
		}
	}
	
	public static void resetPendingOrderPerSession(long pendingOrderCount) {
		int userCount = SessionCache.getUserCount() != 0 ? SessionCache.getUserCount() : 1;
		PENDING_ORDER_PER_SESSION = pendingOrderCount / userCount;
	} 
	
	public static long getPendingOrderPerSession() {
		return PENDING_ORDER_PER_SESSION;
	}
	
	public static void setPendingOrderCount(long pendingOrderCount) {
		PENDING_ORDER_COUNT = pendingOrderCount;
	}
	
	public static long getPendingOrderCount() {
		return PENDING_ORDER_COUNT;
	}
}
