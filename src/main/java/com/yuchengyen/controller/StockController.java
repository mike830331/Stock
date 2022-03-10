package com.yuchengyen.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuchengyen.service.StockService;

@RestController
@RequestMapping(value = "/Stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

	@Autowired
	StockService stockService = new StockService();

	@PostMapping
	public String getProducts() throws IOException, ParseException {
		stockService.getStock();
		return null;
	}
}
