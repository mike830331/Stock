package com.yuchengyen.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yuchengyen.model.ResultModel;

public class Excelservice {

	// Create blank workbook
	public static void exportExcel(List<ResultModel> resultList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet(" Stock Info ");

		// Create row object
		XSSFRow row;
		for (int i = 0; i < 9; i++) {
			row = spreadsheet.createRow(i);
			for (int j = 0; j < resultList.size(); j++) {
				switch (i) {
				case 0:
					row.createCell(j).setCellValue(resultList.get(j).getBloombergCode());
					break;
				case 1:
					row.createCell(j).setCellValue(resultList.get(j).getAverageTimeBetweenTrades());
					break;
				case 2:
					row.createCell(j).setCellValue(resultList.get(j).getMedianTimeBetweenTrades());
					break;
				case 3:
					row.createCell(j).setCellValue(resultList.get(j).getAverageTimeTickChanges());
					break;
				case 4:
					row.createCell(j).setCellValue(resultList.get(j).getMedianTimeTickChanges());
					break;
				case 5:
					row.createCell(j).setCellValue(resultList.get(j).getLongestTimeBetweenTrades());
					break;
				case 6:
					row.createCell(j).setCellValue(resultList.get(j).getLongestTimeTickChanges());
					break;
				case 7:
					row.createCell(j).setCellValue(resultList.get(j).getAverageBuySellDiff());
					break;
				case 8:
					row.createCell(j).setCellValue(resultList.get(j).getLongestTimeTickChanges());
					break;
				}
			}
		}
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("Writesheet.xlsx"));

		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");
	}
}
