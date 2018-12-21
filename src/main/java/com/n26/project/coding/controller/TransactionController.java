package com.n26.project.coding.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;
import com.n26.project.coding.service.TransactionService;
import com.n26.project.coding.utils.DateConverter;

@RestController
@RequestMapping("")
public class TransactionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Value("${transaction.timeIn}")
	private int transTimeIn;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Void> postTransaction(@RequestBody Transactions transactionRequest) {
		
			long currentTime = Instant.now().toEpochMilli();
			System.out.println("currentTime::: "+currentTime);
			
			long transTimeStamp = DateConverter.converToTimeStamp(transactionRequest.getTimestamp());
			//end
			//long requestTimeStamp = transactionRequest.getTimestamp();
			if(transTimeStamp > (currentTime - transTimeIn)) {
				transactionService.addTransaction(transactionRequest.getAmount(), transTimeStamp);
				return ResponseEntity.status(201).build();
			}else {
				logger.error("Transaction Out Of Limit");
				return ResponseEntity.status(204).build();
			}
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<Statistics> findStatistics() {
		
		Statistics statistics = this.transactionService.findStatisticsOfTransaction();
		ResponseEntity<Statistics> response = new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.DELETE)
	public ResponseEntity<Statistics> deleteStatistics() {
		
		this.transactionService.deleteTransactions();
		return ResponseEntity.status(204).build();
		
	}

}
