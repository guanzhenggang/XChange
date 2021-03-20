package org.knowm.xchange.binance;

import java.io.IOException;

import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;

public class BinanceFutureExchangeTest {

	@Test
	public void test() {
		BinanceFutureExchange exchange=ExchangeFactory.INSTANCE.createExchange(BinanceFutureExchange.class);
		try {
			OrderBook book=exchange.getMarketDataService().getOrderBook(CurrencyPair.BTC_USDT);
			System.out.println(book.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
