package org.knowm.xchange.binance.dto.marketdata;

public class LongShortRatioInfo {
	private String symbol;
	private String longShortRatio;
	private String longAccount;
	private String shortAccount;
	private Long timestamp;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLongShortRatio() {
		return longShortRatio;
	}
	public void setLongShortRatio(String longShortRatio) {
		this.longShortRatio = longShortRatio;
	}
	public String getLongAccount() {
		return longAccount;
	}
	public void setLongAccount(String longAccount) {
		this.longAccount = longAccount;
	}
	public String getShortAccount() {
		return shortAccount;
	}
	public void setShortAccount(String shortAccount) {
		this.shortAccount = shortAccount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
