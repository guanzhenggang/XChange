package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.BinanceFuture;
import org.knowm.xchange.binance.BinanceFutureAuthenticated;
import org.knowm.xchange.binance.BinanceFutureExchange;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;
import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;

public class BinanceFutureMarketDataServiceRaw extends BinanceFutureBaseService {

	protected BinanceFutureMarketDataServiceRaw(BinanceFutureExchange exchange, BinanceFutureAuthenticated binance,
			ResilienceRegistries resilienceRegistries) {
		super(exchange, binance, resilienceRegistries);
	}

	public void ping() throws IOException {
		decorateApiCall(() -> binance.ping()).withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER)).call();
	}

	public BinanceOrderbook getBinanceOrderbook(CurrencyPair pair, Integer limit) throws IOException {
		return decorateApiCall(() -> binance.depth(BinanceAdapters.toSymbol(pair),
				limit)).withRetry(retry("depth")).withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER),
						depthPermits(limit)).call();
	}

	public List<BinanceAggTrades> aggTrades(CurrencyPair pair, Long fromId, Long startTime, Long endTime, Integer limit)
			throws IOException {
		return decorateApiCall(() -> binance.aggTrades(BinanceAdapters.toSymbol(pair),
				fromId,
				startTime,
				endTime,
				limit)).withRetry(retry("aggTrades")).withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER),
						aggTradesPermits(limit)).call();
	}

	public List<BinanceKline> klines(CurrencyPair pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
			throws IOException {
		List<Object[]> raw = decorateApiCall(() -> binance.klines(BinanceAdapters.toSymbol(pair),
				interval.code(),
				limit,
				startTime,
				endTime)).withRetry(retry("klines")).withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER)).call();
		return raw.stream().map(obj -> new BinanceKline(pair, interval, obj)).collect(Collectors.toList());
	}


	protected int depthPermits(Integer limit) {
		if (limit == null || limit <= 100) {
			return 1;
		} else if (limit <= 500) {
			return 5;
		} else if (limit <= 1000) {
			return 10;
		}
		return 50;
	}

	protected int aggTradesPermits(Integer limit) {
		if (limit != null && limit > 500) {
			return 2;
		}
		return 1;
	}
}
