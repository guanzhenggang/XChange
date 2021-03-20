package org.knowm.xchange.binance.dto.meta.exchangeinfo;

public class FutureSymbol {

	private String quoteAsset;

	private String icebergAllowed;

	private String ocoAllowed;

	private String isMarginTradingAllowed;

	private String isSpotTradingAllowed;

	private String baseAsset;

	private String symbol;

	private String status;

	private String quotePrecision;

	private String quoteAssetPrecision;

	private String baseAssetPrecision;

	private String[] orderTypes;

	private Filter[] filters;
	
	private String pair;
	
	private String contractType;
	
	private String deliveryDate;
	
	private String underlyingType;
	
	private String[] underlyingSubType;
	
	private String settlePlan;
	
	private String triggerProtect;
	
	private String[] timeInForce;
	
	
}
