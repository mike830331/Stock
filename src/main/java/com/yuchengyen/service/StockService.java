package com.yuchengyen.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yuchengyen.model.DataModel;
import com.yuchengyen.model.ResultModel;
import com.yuchengyen.model.StockModel;
import com.yuchengyen.model.TradePrice;

@Service
public class StockService extends Dataservice {

	DataModel dataModel = Dataservice.getData();

	public String getStock() throws ParseException {
		List<ResultModel> resultModelList = new ArrayList<ResultModel>();
		List<StockModel> totalStockList = dataModel.getStockModel();
		Map<String, List<StockModel>> totalStockListMap = totalStockList.stream()
				.collect(Collectors.groupingBy(StockModel::getBloombergCode));
		int j = 0;
		for (Object totalkey : totalStockListMap.keySet()) {
			List<StockModel> datasymbollist = totalStockListMap.get(totalkey);
			String bloombergCode = datasymbollist.get(0).getBloombergCode();
			ResultModel resultModel = new ResultModel();
			// buy sell diff
			List<Double> buySellDiff = getBuySellDiff(datasymbollist);
			int buySellDiffSize = buySellDiff.size();
			double medianBuySellDiff = 0.0;
			double averageBuySellDiff = 0.0;
			if (buySellDiffSize % 2 == 1) {
				medianBuySellDiff = buySellDiff.get((buySellDiffSize - 1) / 2);
			} else {
				medianBuySellDiff = (buySellDiff.get(buySellDiffSize / 2 - 1) + buySellDiff.get(buySellDiffSize / 2)
						+ 0.0) / 2;
			}
			averageBuySellDiff = buySellDiff.stream().mapToDouble(val -> val).average().orElse(0.0);
			resultModel.setAverageBuySellDiff(averageBuySellDiff);
			resultModel.setMedianBuySellDiff(medianBuySellDiff);
//			Map<Date, List<StockModel>> datelistMap = datasymbollist.stream().filter(c -> c.getUpdateType().equals("1"))
//					.collect(Collectors.groupingBy(StockModel::getDate));
//			for (Object key : datelistMap.keySet()) {
//				// get time diff
//				List<Integer> timeDiff = getTimeDiff(datelistMap.get(key));
//				totalBuySellDiff.addAll(timeDiff);
//			}
			// date trade list Map
			List<TradePrice> totalTradeDiff = new ArrayList<TradePrice>();
			Map<Date, List<StockModel>> dateTradelistMap = datasymbollist.stream()
					.collect(Collectors.groupingBy(StockModel::getDate));
			for (Object key : dateTradelistMap.keySet()) {
				// get time diff
				List<TradePrice> tradePrice = getTradePrice(dateTradelistMap.get(key));
				totalTradeDiff.addAll(tradePrice);
			}
			totalTradeDiff = totalTradeDiff.stream()
					.sorted(Comparator.comparing(TradePrice::getTimeInSecondsPastMidnight))
					.collect(Collectors.toList());
			int tradePricesize = totalTradeDiff.size();
			double medianTimeTickChanges = 0.0;
			double averageTimeTickChanges = 0.0;
			double longestTimeTickChanges = 0.0;
			if (tradePricesize % 2 == 1) {
				medianTimeTickChanges = totalTradeDiff.get((tradePricesize - 1) / 2).getTimeInSecondsPastMidnight();
			} else {
				medianTimeTickChanges = (totalTradeDiff.get(tradePricesize / 2 - 1).getTimeInSecondsPastMidnight()
						+ totalTradeDiff.get(tradePricesize / 2).getTimeInSecondsPastMidnight() + 0.0) / 2;
			}
			averageTimeTickChanges = totalTradeDiff.stream().mapToDouble(val -> val.getTimeInSecondsPastMidnight())
					.average().orElse(0.0);
			longestTimeTickChanges = totalTradeDiff.stream().mapToDouble(val -> val.getTimeInSecondsPastMidnight())
					.max().orElse(0.0);
			// date list Map
			List<Integer> totalTimeDiff = new ArrayList<Integer>();
			Map<Date, List<StockModel>> datelistMap = datasymbollist.stream().filter(c -> c.getUpdateType().equals("1"))
					.collect(Collectors.groupingBy(StockModel::getDate));
			for (Object key : datelistMap.keySet()) {
				// get time diff
				List<Integer> timeDiff = getTimeDiff(datelistMap.get(key));
				totalTimeDiff.addAll(timeDiff);
			}

			totalTimeDiff = totalTimeDiff.stream().sorted().collect(Collectors.toList());
			int size = totalTimeDiff.size();
			double medianTimeBetweenTrades = 0.0;
			double averageTimeBetweenTrades = 0.0;
			double longestTimeBetweenTrades = 0;
			if (size % 2 == 1) {
				medianTimeBetweenTrades = totalTimeDiff.get((size - 1) / 2);
			} else {
				medianTimeBetweenTrades = (totalTimeDiff.get(size / 2 - 1) + totalTimeDiff.get(size / 2) + 0.0) / 2;
			}
			averageTimeBetweenTrades = totalTimeDiff.stream().mapToDouble(val -> val).average().orElse(0.0);
			longestTimeBetweenTrades = totalTimeDiff.stream().mapToDouble(val -> val).max().orElse(0.0);
			resultModel.setBloombergCode(bloombergCode);
			resultModel.setAverageTimeBetweenTrades(averageTimeBetweenTrades);
			resultModel.setAverageTimeTickChanges(averageTimeTickChanges);
			resultModel.setLongestTimeBetweenTrades(longestTimeBetweenTrades);
			resultModel.setLongestTimeTickChanges(longestTimeTickChanges);
			resultModel.setMedianTimeBetweenTrades(medianTimeBetweenTrades);
			resultModel.setMedianTimeTickChanges(medianTimeTickChanges);
			resultModelList.add(resultModel);

		}
		Excelservice excel = new Excelservice();
		try {
			excel.exportExcel(resultModelList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AAA";
	}

	public static List<TradePrice> getTradePrice(List<StockModel> datasymbollist) {
		List<StockModel> listMap = datasymbollist.stream()
				.sorted(Comparator.comparingDouble(StockModel::getTimeInSecondsPastMidnight))
				.collect(Collectors.toList());
//		listMap.forEach(s -> System.out.println(s.getTradePrice()));
		List<TradePrice> tradePricelist = new ArrayList<TradePrice>();
		String initialPrice = listMap.get(0).getTradePrice().toString();
		int initialTimeInSec = listMap.get(0).getTimeInSecondsPastMidnight();
		for (int i = 0; i < listMap.size(); i++) {
			if (listMap.get(i).getTradePrice().toString().equals(initialPrice)) {
//				System.out.println("trade Price is same");
				if (i == listMap.size() - 1) {
					TradePrice tradePricemodel = new TradePrice();
					int timeDiff = listMap.get(i).getTimeInSecondsPastMidnight() - initialTimeInSec;
					tradePricemodel.setTimeInSecondsPastMidnight(timeDiff);
					tradePricemodel.setTradePrice(initialPrice);
					tradePricelist.add(tradePricemodel);
				}
			} else {
				TradePrice tradePricemodel = new TradePrice();
				int timeDiff = listMap.get(i).getTimeInSecondsPastMidnight() - initialTimeInSec;
				tradePricemodel.setTimeInSecondsPastMidnight(timeDiff);
				tradePricemodel.setTradePrice(initialPrice);
				tradePricelist.add(tradePricemodel);
				initialPrice = listMap.get(i).getTradePrice().toString();
				initialTimeInSec = listMap.get(i).getTimeInSecondsPastMidnight();
			}
		}

		return tradePricelist;
	}

	public static List<Integer> getTimeDiff(List<StockModel> datasymbollist) {
		List<Integer> TimeDistinctSort = datasymbollist.stream().map(StockModel::getTimeInSecondsPastMidnight)
				.distinct().sorted().collect(Collectors.toList());
//		TimeDistinctSort.forEach(s -> System.out.println(s));
		List<Integer> TimeDiff = new ArrayList<Integer>();
		for (int i = 1; i < TimeDistinctSort.size(); i++) {
			int timediff = TimeDistinctSort.get(i) - TimeDistinctSort.get(i - 1);
			TimeDiff.add(timediff);
		}
		return TimeDiff;
	}

	public static List<Double> getBuySellDiff(List<StockModel> datasymbollist) {
		List<Double> buySellDiffList = new ArrayList<Double>();
		for (int i = 1; i < datasymbollist.size(); i++) {
			double buySellDiff = Double.parseDouble(datasymbollist.get(i).getBidPrice())
					- Double.parseDouble(datasymbollist.get(i).getAskPrice());
			buySellDiffList.add(buySellDiff);
		}
		return buySellDiffList;
	}

}
