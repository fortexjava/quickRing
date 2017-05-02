package com.fortex.quickRing.model;

public class UserModel {
	
	private Integer domain;
	private Integer quoteService;
	private String userName;
	public UserModel(Integer domain, Integer quoteService, String userName) {
		this.domain = domain;
		this.quoteService = quoteService;
		this.userName = userName;
	}
	public Integer getDomain() {
		return domain;
	}
	public void setDomain(Integer domain) {
		this.domain = domain;
	}
	public Integer getQuoteService() {
		return quoteService;
	}
	public void setQuoteService(Integer quoteService) {
		this.quoteService = quoteService;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
