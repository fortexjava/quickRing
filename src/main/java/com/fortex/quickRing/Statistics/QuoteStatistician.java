package com.fortex.quickRing.Statistics;

import java.math.BigDecimal;

import com.fortex.quickRing.utils.DoubleUtil;

public class QuoteStatistician {
	
	private long totalReceived;
	private long totalSended;
	private long totalDropped;
	private double proccessingTimeMax;
	private double proccessingTimeMin;
	private long receivedTime;
	private long proccessingTimeTotal;
	private static QuoteStatistician quoteStatistician;
	
	public static QuoteStatistician getInstance() {
		if(quoteStatistician == null)
			quoteStatistician = new QuoteStatistician();
		return quoteStatistician;
	}
	public long getTotalReceived() {
		return totalReceived;
	}
	public void addTotalReceived() {
		++totalReceived;
	}
	public long getTotalSended() {
		return totalSended;
	}
	public void addTotalSended() {
		++totalSended;
	}
	public double getDropRatio() {
		double result = 0;
		if (totalSended + totalDropped != 0)
			result = DoubleUtil.div(totalDropped, totalSended + totalDropped, 2);
		return result;
	}
	public void addtotalDropped() {
		++totalDropped;
	}
	public double getProccessingTimeAvg() {
		double t = totalSended != 0 ? totalSended : 1;
		return new BigDecimal(proccessingTimeTotal / 1000000 / t).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getProccessingTimeMax() {
		return proccessingTimeMax;
	}
	public void setProccessingTimeMax(double proccessingTimeMax) {
		this.proccessingTimeMax = proccessingTimeMax;
	}
	public double getProccessingTimeMin() {
		return proccessingTimeMin;
	}
	public void setProccessingTimeMin(double proccessingTimeMin) {
		this.proccessingTimeMin = proccessingTimeMin;
	}
	public long getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(long receivedTime) {
		this.receivedTime = receivedTime;
	}
	
	public void clear() {
		totalReceived = 0;
		totalSended = 0;
		totalDropped = 0;
		proccessingTimeMax = 0;
		proccessingTimeMin = 0;
		receivedTime = 0;
		proccessingTimeTotal = 0;
	}
	
	
	public void proccessForTime(long proccessingTime) {
		double doubleProccessingTime = DoubleUtil.div(proccessingTime, 1000000, 2);
		if (this.proccessingTimeMin == 0 || this.proccessingTimeMin >= doubleProccessingTime)
			proccessingTimeMin = doubleProccessingTime;
		if (proccessingTimeMax <= doubleProccessingTime)
			proccessingTimeMax = doubleProccessingTime;
		proccessingTimeTotal += proccessingTime;
	}
}
