package com.fortex.quickRing.model;

import quickfix.SessionID;

public class SessionIDMarketDepthModel {
	private SessionID sessionID;
	private int marketDepth;
	private String mdReqId;
	
	public SessionIDMarketDepthModel(SessionID sessionID, int marketDepth, String mdReqId) {
		this.sessionID = sessionID;
		this.marketDepth = marketDepth;
		this.mdReqId = mdReqId;
	}
	public SessionID getSessionID() {
		return sessionID;
	}
	public void setSessionID(SessionID sessionID) {
		this.sessionID = sessionID;
	}
	public int getMarketDepth() {
		return marketDepth;
	}
	public void setMarketDepth(int marketDepth) {
		this.marketDepth = marketDepth;
	}
	@Override
	public boolean equals(Object obj) {
		SessionIDMarketDepthModel s = (SessionIDMarketDepthModel)obj;
		return this.sessionID.equals(s.getSessionID());
	}
	@Override
	public int hashCode() {
		return this.sessionID.hashCode();
	}
	public String getMdReqId() {
		return mdReqId;
	}
	public void setMdReqId(String mdReqId) {
		this.mdReqId = mdReqId;
	}
	
}
