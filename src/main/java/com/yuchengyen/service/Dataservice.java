package com.yuchengyen.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.yuchengyen.model.DataModel;
import com.yuchengyen.model.StockModel;

public class Dataservice {

	public static DataModel getData() {
		DataModel dataModel = new DataModel();
		String filePath = "/Users/yenyucheng/Desktop/workspace/binpacking/src/main/scandi.csv";
		dataModel = readTxtFileIntoStringArrList(filePath);
		return dataModel;
	}

	protected static DataModel readTxtFileIntoStringArrList(String filePath) {

		DataModel dataModel = new DataModel();
		List<StockModel> list = new ArrayList<StockModel>();
		final String delimiter = ",";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// consider encoding
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] token = lineTxt.split(delimiter);
					if (token[14].contains("XT")) {
						StockModel stockModel = new StockModel();
						stockModel.setBloombergCode(token[0]);
						stockModel.setBidPrice(token[2]);
						stockModel.setAskPrice(token[3]);
						stockModel.setTradePrice(Double.parseDouble(token[4]));
						stockModel.setBidVolume(token[5]);
						stockModel.setAskVolume(token[6]);
						stockModel.setTradeVolume(Integer.parseInt(token[7]));
						stockModel.setUpdateType(token[8]);
						stockModel.setDate(transferdate(token[10]));
						stockModel.setTimeInSecondsPastMidnight(
								Integer.parseInt(token[11].substring(0, token[11].indexOf("."))));
						stockModel.setConditionCodes(token[14]);
						list.add(stockModel);
					}
				}
				List<String> symbols = list.stream().map(StockModel::getBloombergCode).distinct()
						.collect(Collectors.toList());

				bufferedReader.close();
				read.close();
				dataModel.setStockModel(list);
				dataModel.setSymbols(symbols);
				System.out.println("int:" + list.size());
			} else {
				System.out.println("File Not Found Exception");
			}
		} catch (Exception e) {
			System.out.println("Error reading file content");
			e.printStackTrace();
		}
		return dataModel;
	}

	public static Date transferdate(String date) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date outPut = inputFormat.parse(date);
		return outPut;
	}
}
