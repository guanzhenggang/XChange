package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;

import org.knowm.xchange.binance.BinanceFuture;
import org.knowm.xchange.binance.BinanceFutureAuthenticated;
import org.knowm.xchange.binance.BinanceFutureExchange;
import org.knowm.xchange.binance.dto.meta.BinanceSystemStatus;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceFutureExchangeInfo;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

public class BinanceFutureBaseService extends BaseResilientExchangeService<BinanceFutureExchange> {

	BinanceFutureAuthenticated binance;
	String apiKey;
	ParamsDigest signatureCreator;

	protected BinanceFutureBaseService(BinanceFutureExchange exchange, BinanceFutureAuthenticated binance,
			ResilienceRegistries resilienceRegistries) {
		super(exchange, resilienceRegistries);
		this.binance = binance;
		this.apiKey = exchange.getExchangeSpecification().getApiKey();
		this.signatureCreator = BinanceHmacDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
	}

	public Long getRecvWindow() {
		return (Long) exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
	}

	public SynchronizedValueFactory<Long> getTimestampFactory() {
		return exchange.getTimestampFactory();
	}

	public BinanceFutureExchangeInfo getExchangeInfo() throws IOException {
		return decorateApiCall(binance::exchangeInfo).withRetry(retry("exchangeInfo"))
				.withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER)).call();
	}

	public BinanceSystemStatus getSystemStatus() throws IOException {
		return decorateApiCall(binance::systemStatus).call();
	}
}
