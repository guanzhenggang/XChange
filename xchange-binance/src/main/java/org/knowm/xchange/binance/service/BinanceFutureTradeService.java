package org.knowm.xchange.binance.service;

import org.knowm.xchange.binance.BinanceFutureAuthenticated;
import org.knowm.xchange.binance.BinanceFutureExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.trade.TradeService;

public class BinanceFutureTradeService extends BinanceFutureBaseService implements TradeService{

	protected BinanceFutureTradeService(BinanceFutureExchange exchange, BinanceFutureAuthenticated binance,
			ResilienceRegistries resilienceRegistries) {
		super(exchange, binance, resilienceRegistries);
		// TODO Auto-generated constructor stub
	}

	
}
