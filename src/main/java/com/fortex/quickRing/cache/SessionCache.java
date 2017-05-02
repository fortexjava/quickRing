package com.fortex.quickRing.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.fortex.quickRing.model.UserModel;

import quickfix.Session;
import quickfix.SessionID;

public class SessionCache {
	private static final Map<SERVER_TYPE, Session> SERVER_SESSION_CACHE = new ConcurrentHashMap<>();
	private static final Map<SessionID, UserModel> SESSIONID_ACCOUNT_CACHE = new ConcurrentHashMap<>();
	private static final Map<String, SessionID> ACCOUNT_SESSION_CACHE = new ConcurrentHashMap<>();
	
	public enum SERVER_TYPE{FQS,FTS}
	public static void setSession (SERVER_TYPE serverType, Session session) {
		SERVER_SESSION_CACHE.put(serverType, session);
	}
	
	public static Session getSession(SERVER_TYPE serverType) {
		return SERVER_SESSION_CACHE.get(serverType);
	}
	
	public static void removeSessionId(SessionID sessionID) {
		SESSIONID_ACCOUNT_CACHE.remove(sessionID);
	}
	
	public static void putUserModelBySessionID(SessionID sessionID, UserModel user) {
		SESSIONID_ACCOUNT_CACHE.put(sessionID, user);
	}

	public static UserModel getUserModelBySessionId(SessionID sessionID) {
		return SESSIONID_ACCOUNT_CACHE.get(sessionID);
	}
	/*
	public static void removeSessionDomainCache(SessionID sessionID) {
		SESSIONID_DOMAIN_CACHE.remove(sessionID);
	}
	*/
	public static int getUserCount() {
		return SESSIONID_ACCOUNT_CACHE.size();
	}
	
	public static void putAccountSessionId(String account, SessionID sessionID) {
		ACCOUNT_SESSION_CACHE.put(account, sessionID);
	}
	
	public static void removeSessionIdForAccount(SessionID sessionID) {
		Iterator<Entry<String, SessionID>> it = ACCOUNT_SESSION_CACHE.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, SessionID> entry = it.next();
			if (sessionID.equals(entry.getValue())) {
				it.remove();
				break;
			}
		}
	}
	
	public static SessionID getSessionIdByAccount(String account) {
		return ACCOUNT_SESSION_CACHE.get(account);
	}
	
	public static List<String> getUserNames() {
		List<String> accounts = new ArrayList<String>();
		for (Entry<SessionID, UserModel> entry : SESSIONID_ACCOUNT_CACHE.entrySet()) {
			accounts.add(entry.getValue().getUserName());
		} 
		
		return accounts;
	}
	
}
