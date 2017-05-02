package com.fortex.quickRing.model;

public class QuoteOffsetModel {
	private double bid;
	private double ask;
	public QuoteOffsetModel() {
	
	}
	public QuoteOffsetModel(double bid, double ask) {
		this.bid = bid;
		this.ask = ask;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	
}
