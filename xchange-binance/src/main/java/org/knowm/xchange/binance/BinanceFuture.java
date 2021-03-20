package org.knowm.xchange.binance;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;
import org.knowm.xchange.binance.dto.marketdata.BinanceOrderbook;
import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.dto.marketdata.ForceOrder;
import org.knowm.xchange.binance.dto.marketdata.FundingInfo;
import org.knowm.xchange.binance.dto.marketdata.LongShortRatioInfo;
import org.knowm.xchange.binance.dto.marketdata.OpenInterestInfo;
import org.knowm.xchange.binance.dto.marketdata.PriceAndFundingInfo;
import org.knowm.xchange.binance.dto.marketdata.SumOpenInterestInfo;
import org.knowm.xchange.binance.dto.marketdata.TakerRadioInfo;
import org.knowm.xchange.binance.dto.meta.BinanceSystemStatus;
import org.knowm.xchange.binance.dto.meta.BinanceTime;
import org.knowm.xchange.binance.dto.meta.exchangeinfo.BinanceFutureExchangeInfo;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinanceFuture {

	@GET
	@Path("wapi/v3/systemStatus.html")
	/**
	 * Fetch system status which is normal or system maintenance.
	 *
	 * @throws IOException
	 */
	BinanceSystemStatus systemStatus() throws IOException;

	@GET
	@Path("/fapi/v1/ping")
	Object ping() throws IOException;

	@GET
	@Path("")
	BinanceTime time() throws IOException;

	@GET
	@Path("/fapi/v1/exchangeInfo")
	BinanceFutureExchangeInfo exchangeInfo() throws IOException;

	@GET
	@Path("/fapi/v1/depth")
	BinanceOrderbook depth(@QueryParam("symbol") String symbol, @QueryParam("limit") Integer limit)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/aggTrades")
	List<BinanceAggTrades> aggTrades(@QueryParam("symbol") String symbol, @QueryParam("fromId") Long fromId, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime, @QueryParam("limit") Integer limit)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/klines")
	List<Object[]> klines(@QueryParam("symbol") String symbol, @QueryParam("interval") String interval, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/continuousKlines")
	List<Object[]> continuousKlines(@QueryParam("pair") String pair, @QueryParam("contractType") String contractType, @QueryParam("interval") String interval, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/indexPriceKlines")
	List<Object[]> indexPriceKlines(@QueryParam("pair") String pair, @QueryParam("contractType") String contractType, @QueryParam("interval") String interval, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/markPriceKlines")
	List<Object[]> markPriceKlines(@QueryParam("symbol") String symbol, @QueryParam("contractType") String contractType, @QueryParam("interval") String interval, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/premiumIndex")
	PriceAndFundingInfo premiumIndex(@QueryParam("symbol") String symbol) throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/fundingRate")
	List<FundingInfo> fundingRate(@QueryParam("symbol") String symbol, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime, @QueryParam("limit") Integer limit)
			throws IOException, BinanceException;;

	@GET
	@Path("/fapi/v1/ticker/24hr")
	List<BinanceTicker24h> ticker24h(@QueryParam("symbol") String symbol) throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/allForceOrders")
	List<ForceOrder> allForceOrders(@QueryParam("symbol") String symbol, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime, @QueryParam("limit") Integer limit)
			throws IOException, BinanceException;

	@GET
	@Path("/fapi/v1/openInterest")
	OpenInterestInfo openInterest(@QueryParam("symbol") String symbol) throws IOException, BinanceException;

	@GET
	@Path("/futures/data/openInterestHist")
	List<SumOpenInterestInfo> openInterestHist(@QueryParam("symbol") String symbol, @QueryParam("period") String period, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/futures/data/topLongShortAccountRatio")
	List<LongShortRatioInfo> topLongShortAccountRatio(@QueryParam("symbol") String symbol, @QueryParam("period") String period, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/futures/data/topLongShortAccountRatio")
	List<LongShortRatioInfo> topLongShortPositionRatio(@QueryParam("symbol") String symbol, @QueryParam("period") String period, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/futures/data/topLongShortAccountRatio")
	List<LongShortRatioInfo> globalLongShortAccountRatio(@QueryParam("symbol") String symbol, @QueryParam("period") String period, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;

	@GET
	@Path("/futures/data/takerlongshortRatio")
	List<TakerRadioInfo> takerlongshortRatio(@QueryParam("symbol") String symbol, @QueryParam("period") String period, @QueryParam("limit") Integer limit, @QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime)
			throws IOException, BinanceException;
}
