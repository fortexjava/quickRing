/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.model;

import quickfix.SessionID;

/**
 * @author Ivan Huo
 *
 */
public class SessionResponsedModel {
	private SessionID sessionID;
	private int originalMsgSeqNum;
	private boolean responsed;
	private long generatedTime;
	
	public SessionID getSessionID() {
		return sessionID;
	}
	public void setSessionID(SessionID sessionID) {
		this.sessionID = sessionID;
	}
	
	public int getOriginalMsgSeqNum() {
		return originalMsgSeqNum;
	}
	public void setOriginalMsgSeqNum(int originalMsgSeqNum) {
		this.originalMsgSeqNum = originalMsgSeqNum;
	}
	public SessionResponsedModel(SessionID sessionID, int originalMsgSeqNum){
		this.sessionID=sessionID;
		this.originalMsgSeqNum = originalMsgSeqNum;
		this.generatedTime = System.currentTimeMillis();
	}
	public boolean isResponsed() {
		return responsed;
	}
	public void setResponsed(boolean responsed) {
		this.responsed = responsed;
	}
	public long getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(long generatedTime) {
		this.generatedTime = generatedTime;
	}
}
