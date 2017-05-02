package com.fortex.quickRing.utils;

import java.math.BigDecimal;

/**
 * 
 * @author Patrick Chi
 *
 */
public class DoubleUtil {
	//public static final int DEF_SCALE  = 6;
	
	public static double add(double n1, double n2, int scale) {
		BigDecimal b1 = new BigDecimal(n1);
		BigDecimal b2 = new BigDecimal(n2);
		return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double sub(double n1, double n2, int scale) {
		BigDecimal b1 = new BigDecimal(n1);
		BigDecimal b2 = new BigDecimal(n2);
		return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double mul(double n1, double n2, int scale) {
		BigDecimal b1 = new BigDecimal(n1);
		BigDecimal b2 = new BigDecimal(n2);
		return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double div(double n1, double n2, int scale) {
		BigDecimal b1 = new BigDecimal(n1);
		BigDecimal b2 = new BigDecimal(n2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
}
