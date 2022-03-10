package com.yuchengyen.model;

public class ResultModel {
	private String bloombergCode;
	private double medianTimeBetweenTrades;
	private double averageTimeBetweenTrades;
	private double longestTimeBetweenTrades;
	private double medianTimeTickChanges;
	private double averageTimeTickChanges;
	private double longestTimeTickChanges;
	private double medianBuySellDiff;
	private double averageBuySellDiff;

	public String getBloombergCode() {
		return bloombergCode;
	}

	public void setBloombergCode(String bloombergCode) {
		this.bloombergCode = bloombergCode;
	}

	public double getMedianTimeBetweenTrades() {
		return medianTimeBetweenTrades;
	}

	public void setMedianTimeBetweenTrades(double medianTimeBetweenTrades) {
		this.medianTimeBetweenTrades = medianTimeBetweenTrades;
	}

	public double getAverageTimeBetweenTrades() {
		return averageTimeBetweenTrades;
	}

	public void setAverageTimeBetweenTrades(double averageTimeBetweenTrades) {
		this.averageTimeBetweenTrades = averageTimeBetweenTrades;
	}

	public double getLongestTimeBetweenTrades() {
		return longestTimeBetweenTrades;
	}

	public void setLongestTimeBetweenTrades(double longestTimeBetweenTrades) {
		this.longestTimeBetweenTrades = longestTimeBetweenTrades;
	}

	public double getMedianTimeTickChanges() {
		return medianTimeTickChanges;
	}

	public void setMedianTimeTickChanges(double medianTimeTickChanges) {
		this.medianTimeTickChanges = medianTimeTickChanges;
	}

	public double getAverageTimeTickChanges() {
		return averageTimeTickChanges;
	}

	public void setAverageTimeTickChanges(double averageTimeTickChanges) {
		this.averageTimeTickChanges = averageTimeTickChanges;
	}

	public double getLongestTimeTickChanges() {
		return longestTimeTickChanges;
	}

	public void setLongestTimeTickChanges(double longestTimeTickChanges) {
		this.longestTimeTickChanges = longestTimeTickChanges;
	}

	public double getMedianBuySellDiff() {
		return medianBuySellDiff;
	}

	public void setMedianBuySellDiff(double medianBuySellDiff) {
		this.medianBuySellDiff = medianBuySellDiff;
	}

	public double getAverageBuySellDiff() {
		return averageBuySellDiff;
	}

	public void setAverageBuySellDiff(double averageBuySellDiff) {
		this.averageBuySellDiff = averageBuySellDiff;
	}

}
