package org.knowm.xchange.binance;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.knowm.xchange.binance.service.BinanceFutureMarketDataService;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.utils.AuthUtils;

import si.mazi.rescu.SynchronizedValueFactory;

public class BinanceFutureExchange extends BaseExchange {

	private static ResilienceRegistries RESILIENCE_REGISTRIES;
	private BinanceExchangeInfo exchangeInfo;
	private BinanceFutureAuthenticated binance;
	private SynchronizedValueFactory<Long> timestampFactory;

	public static ResilienceRegistries getRESILIENCE_REGISTRIES() {
		return RESILIENCE_REGISTRIES;
	}

	public static void setRESILIENCE_REGISTRIES(ResilienceRegistries rESILIENCE_REGISTRIES) {
		RESILIENCE_REGISTRIES = rESILIENCE_REGISTRIES;
	}

	public BinanceExchangeInfo getExchangeInfo() {
		return exchangeInfo;
	}

	public void setExchangeInfo(BinanceExchangeInfo exchangeInfo) {
		this.exchangeInfo = exchangeInfo;
	}

	public BinanceFutureAuthenticated getBinance() {
		return binance;
	}

	public void setBinance(BinanceFutureAuthenticated binance) {
		this.binance = binance;
	}

	public SynchronizedValueFactory<Long> getTimestampFactory() {
		return timestampFactory;
	}

	public void setTimestampFactory(SynchronizedValueFactory<Long> timestampFactory) {
		this.timestampFactory = timestampFactory;
	}

	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		 ExchangeSpecification spec = new ExchangeSpecification(this.getClass());
		    spec.setSslUri("https://fapi.binance.com");
		    spec.setHost("fapi.binance.com");
		    spec.setPort(80);
		    spec.setExchangeName("Binance Future");
		    spec.setExchangeDescription("Binance Future Exchange.");
		    AuthUtils.setApiAndSecretKey(spec, "binance-future");
		    return spec;
	}

	@Override
	protected void initServices() {
		this.binance = ExchangeRestProxyBuilder.forInterface(BinanceFutureAuthenticated.class,
				getExchangeSpecification()).build();
		this.timestampFactory = new BinanceFutureTimestampFactory(binance, getExchangeSpecification().getResilience(),
				getResilienceRegistries());
		this.marketDataService=new BinanceFutureMarketDataService(this,binance,RESILIENCE_REGISTRIES);
	}

}
