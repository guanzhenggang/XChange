package org.knowm.xchange.binance.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.knowm.xchange.binance.BinanceErrorAdapter;
import org.knowm.xchange.binance.BinanceFutureAuthenticated;
import org.knowm.xchange.binance.BinanceFutureExchange;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BinanceFutureMarketDataService extends BinanceFutureMarketDataServiceRaw implements MarketDataService {

	public BinanceFutureMarketDataService(BinanceFutureExchange exchange, BinanceFutureAuthenticated binance,
			ResilienceRegistries resilienceRegistries) {
		super(exchange, binance, resilienceRegistries);
	}

	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
		BinanceOrderbook book = getBinanceOrderbook(currencyPair,
				null);
		try {
			int limitDepth = 100;

			if (args != null && args.length == 1) {
				Object arg0 = args[0];
				if (!(arg0 instanceof Integer)) {
					throw new ExchangeException("Argument 0 must be an Integer!");
				} else {
					limitDepth = (Integer) arg0;
				}
			}
			BinanceOrderbook binanceOrderbook = getBinanceOrderbook(currencyPair,
					limitDepth);
			return convertOrderBook(binanceOrderbook,
					currencyPair);
		} catch (BinanceException e) {
			throw BinanceErrorAdapter.adapt(e);
		}
	}

	public static OrderBook convertOrderBook(BinanceOrderbook ob, CurrencyPair pair) {
		List<LimitOrder> bids = ob.bids.entrySet().stream()
				.map(e -> new LimitOrder(OrderType.BID, e.getValue(), pair, null, null, e.getKey()))
				.collect(Collectors.toList());
		List<LimitOrder> asks = ob.asks.entrySet().stream()
				.map(e -> new LimitOrder(OrderType.ASK, e.getValue(), pair, null, null, e.getKey()))
				.collect(Collectors.toList());
		return new OrderBook(null, asks, bids);
	}
}
