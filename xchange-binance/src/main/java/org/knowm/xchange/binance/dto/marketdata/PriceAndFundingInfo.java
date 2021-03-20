package org.knowm.xchange.binance.dto.marketdata;

public class PriceAndFundingInfo {
	private String symbol;
	private String marketPrice;
	private String indexPrice;
	private String lastFundingRate;
	private String nextFundingTime;
	private String interestRate;
	private Long time;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getIndexPrice() {
		return indexPrice;
	}
	public void setIndexPrice(String indexPrice) {
		this.indexPrice = indexPrice;
	}
	public String getLastFundingRate() {
		return lastFundingRate;
	}
	public void setLastFundingRate(String lastFundingRate) {
		this.lastFundingRate = lastFundingRate;
	}
	public String getNextFundingTime() {
		return nextFundingTime;
	}
	public void setNextFundingTime(String nextFundingTime) {
		this.nextFundingTime = nextFundingTime;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
}
