package com.yuchengyen.model;

import java.util.Date;

public class StockModel {

	private String bloombergCode;
	private String bidPrice;
	private String askPrice;
	private Double tradePrice;
	private String bidVolume;
	private String askVolume;
	private int tradeVolume;
	private String updateType;
	private Date date;
	private int timeInSecondsPastMidnight;
	private String conditionCodes;
	public String getBloombergCode() {
		return bloombergCode;
	}
	public void setBloombergCode(String bloombergCode) {
		this.bloombergCode = bloombergCode;
	}
	public String getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	public String getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(String askPrice) {
		this.askPrice = askPrice;
	}
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public String getBidVolume() {
		return bidVolume;
	}
	public void setBidVolume(String bidVolume) {
		this.bidVolume = bidVolume;
	}
	public String getAskVolume() {
		return askVolume;
	}
	public void setAskVolume(String askVolume) {
		this.askVolume = askVolume;
	}
	
	public int getTradeVolume() {
		return tradeVolume;
	}
	public void setTradeVolume(int tradeVolume) {
		this.tradeVolume = tradeVolume;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTimeInSecondsPastMidnight() {
		return timeInSecondsPastMidnight;
	}
	public void setTimeInSecondsPastMidnight(int timeInSecondsPastMidnight) {
		this.timeInSecondsPastMidnight = timeInSecondsPastMidnight;
	}
	public String getConditionCodes() {
		return conditionCodes;
	}
	public void setConditionCodes(String conditionCodes) {
		this.conditionCodes = conditionCodes;
	}

}
