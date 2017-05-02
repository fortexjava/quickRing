package com.fortex.quickRing.Statistics;

import java.math.BigDecimal;

import com.fortex.quickRing.utils.DoubleUtil;

public class TradeStatistician {
	private long orderReceivedTotal;
	private long orderRoutedTotal;
	private long orderRouteRespTotal;
	private long orderSendToClientTotal;
	
	private long orderRouteReject;
	private long orderRouteFill;
	private double orderRouteProccessingTimeMin;
	private double orderRouteProccessingTimeMax;
	private double orderRouteRespProccessingTimeMin;
	private double orderRouteRespProccessingTimeMax;
	private long orderRouteProccessingTimeTotal;
	private long orderRouteRespProccessingTimeTotal;
	private static TradeStatistician tradeStatistician;
	
	
	public static TradeStatistician getInstance() {
		if (tradeStatistician == null)
			tradeStatistician = new TradeStatistician();
		return tradeStatistician;
	}
	
	public long getOrderReceivedTotal() {
		return orderReceivedTotal;
	}
	public void addOrderReceivedTotal() {
		++orderReceivedTotal;
	}
	public long getOrderRoutedTotal() {
		return orderRoutedTotal;
	}
	public void addOrderRoutedTotal() {
		++orderRoutedTotal;
	}
	public long getOrderRouteRespTotal() {
		return orderRouteRespTotal;
	}
	public void addOrderRouteRespTotal() {
		++orderRouteRespTotal;
	}
	
	public long getOrderRouteReject() {
		return orderRouteReject;
	}
	public void addOrderRouteReject() {
		++orderRouteReject;
	}
	public long getOrderRouteFill() {
		return orderRouteFill;
	}
	public void addOrderRouteFill() {
		++orderRouteFill;
	}
	public double getOrderRouteProccessingTimeMin() {
		return orderRouteProccessingTimeMin;
	}
	public void setOrderRouteProccessingTimeMin(double orderRouteProccessingTimeMin) {
		this.orderRouteProccessingTimeMin = orderRouteProccessingTimeMin;
	}
	public double getOrderRouteProccessingTimeAvg() {
		double t = orderRoutedTotal != 0 ? orderRoutedTotal : 1;
		return new BigDecimal(orderRouteProccessingTimeTotal / (double)1000000
				/ t).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getOrderRouteProccessingTimeMax() {
		return orderRouteProccessingTimeMax;
	}
	public void setOrderRouteProccessingTimeMax(double orderRouteProccessingTimeMax) {
		this.orderRouteProccessingTimeMax = orderRouteProccessingTimeMax;
	}
	public double getOrderRouteRespProccessingTimeMin() {
		return orderRouteRespProccessingTimeMin;
	}
	public void setOrderRouteRespProccessingTimeMin(double orderRouteRespProccessingTimeMin) {
		this.orderRouteRespProccessingTimeMin = orderRouteRespProccessingTimeMin;
	}
	public double getOrderRouteRespProccessingTimeAvg() {
		double t = orderRouteRespTotal != 0 ? orderRouteRespTotal : 1;
		return new BigDecimal(orderRouteRespProccessingTimeTotal / (double)1000000
				/ t).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double getOrderRouteRespProccessingTimeMax() {
		return orderRouteRespProccessingTimeMax;
	}
	public void setOrderRouteRespProccessingTimeMax(double orderRouteRespProccessingTimeMax) {
		this.orderRouteRespProccessingTimeMax = orderRouteRespProccessingTimeMax;
	}
	
	public long getOrderRouteProccessingTimeTotal() {
		return orderRouteProccessingTimeTotal;
	}
	public void setOrderRouteProccessingTimeTotal(long orderRouteProccessingTimeTotal) {
		this.orderRouteProccessingTimeTotal = orderRouteProccessingTimeTotal;
	}
	public long getOrderRouteRespProccessingTimeTotal() {
		return orderRouteRespProccessingTimeTotal;
	}
	public void setOrderRouteRespProccessingTimeTotal(long orderRouteRespProccessingTimeTotal) {
		this.orderRouteRespProccessingTimeTotal = orderRouteRespProccessingTimeTotal;
	}
	
	public long getOrderSendToClientTotal() {
		return orderSendToClientTotal;
	}
	public void addOrderSendToClientTotal() {
		++orderSendToClientTotal;
	}
	
	public void proccessForRouteTime(long orderRouteProccessingTime) {
		double doubleOrderRouteProccessingTime = DoubleUtil.div(orderRouteProccessingTime, 1000000, 2);
		if (this.orderRouteProccessingTimeMin == 0 || this.orderRouteProccessingTimeMin >= doubleOrderRouteProccessingTime)
			this.orderRouteProccessingTimeMin = doubleOrderRouteProccessingTime;
		if (this.orderRouteProccessingTimeMax <= doubleOrderRouteProccessingTime)
			this.orderRouteProccessingTimeMax = doubleOrderRouteProccessingTime;
		this.orderRouteProccessingTimeTotal += orderRouteProccessingTime;
		++this.orderRoutedTotal;	
	}
	
	public void proccessForRespTime(long orderRouteRespProccessingTime) {
		double doubleOrderRouteRespProccessingTime = DoubleUtil.div(orderRouteRespProccessingTime, 1000000, 2);
		if (this.orderRouteRespProccessingTimeMin == 0 || this.orderRouteRespProccessingTimeMin >= doubleOrderRouteRespProccessingTime) 
			this.orderRouteRespProccessingTimeMin = doubleOrderRouteRespProccessingTime;
		if (this.orderRouteRespProccessingTimeMax <= doubleOrderRouteRespProccessingTime) 
			this.orderRouteRespProccessingTimeMax = doubleOrderRouteRespProccessingTime;
		this.orderRouteRespProccessingTimeTotal += orderRouteRespProccessingTime;
		++this.orderSendToClientTotal;
	}
	
	public void clear() {
		orderReceivedTotal = 0;
		orderRoutedTotal = 0;
		orderRouteRespTotal = 0;
		orderRouteReject = 0;
		orderRouteFill = 0;
		orderRouteProccessingTimeMin = 0;
		orderRouteProccessingTimeMax = 0;
		orderRouteRespProccessingTimeMin = 0;
		orderRouteRespProccessingTimeMax = 0;
		orderRouteProccessingTimeTotal = 0;
		orderRouteRespProccessingTimeTotal = 0;
		orderSendToClientTotal = 0;
	}
	
}
