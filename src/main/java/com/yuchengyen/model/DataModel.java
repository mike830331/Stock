package com.yuchengyen.model;

import java.util.List;

public class DataModel {
	public List<String> symbols;
//	public List<Integer> times;
	public List<StockModel> stockModel;

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

//	public List<Integer> getTimes() {
//		return times;
//	}
//
//	public void setTimes(List<Integer> times) {
//		this.times = times;
//	}

	public List<StockModel> getStockModel() {
		return stockModel;
	}

	public void setStockModel(List<StockModel> stockModel) {
		this.stockModel = stockModel;
	}

}
