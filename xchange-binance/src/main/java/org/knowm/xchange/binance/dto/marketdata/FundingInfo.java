package org.knowm.xchange.binance.dto.marketdata;

public class FundingInfo {
	private String symbol;
	private String fundingRate;
	private Long fundingTime;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getFundingRate() {
		return fundingRate;
	}
	public void setFundingRate(String fundingRate) {
		this.fundingRate = fundingRate;
	}
	public Long getFundingTime() {
		return fundingTime;
	}
	public void setFundingTime(Long fundingTime) {
		this.fundingTime = fundingTime;
	}
	
}
