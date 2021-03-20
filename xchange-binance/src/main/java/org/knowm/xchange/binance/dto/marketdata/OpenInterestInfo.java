package org.knowm.xchange.binance.dto.marketdata;

public class OpenInterestInfo {
	private String openInterest;
	private String symbol;
	private Long time;
	public String getOpenInterest() {
		return openInterest;
	}
	public void setOpenInterest(String openInterest) {
		this.openInterest = openInterest;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
}
