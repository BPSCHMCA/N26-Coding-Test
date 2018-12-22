package com.n26.project.coding.controller;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;
import com.n26.project.coding.exceptions.InvalidParseException;
import com.n26.project.coding.exceptions.OldTransactionException;
import com.n26.project.coding.service.TransactionService;
import com.n26.project.coding.utils.BigDecimalConverter;
import com.n26.project.coding.utils.DateConverter;

/**
 * 
 * @author RajeswariT
 *
 */

@RestController
public class TransactionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Value("${transaction.timeInSeconds}")
	private int transTimeIn;
	
	@Autowired
	private TransactionService transactionService;
	
	/*
	 * API to POST transactions into a MAP with in last 60 seconds of Current UTC time zone. 
	 */
	@PostMapping("/transactions")
	public ResponseEntity<Void> postTransaction(@Valid @RequestBody Transactions transactionRequest) throws InvalidParseException, OldTransactionException  {
			logger.info("Begin: postTransaction");
			Instant currentTime = Instant.now();
			System.out.println("Instant.now()123::: "+currentTime);
			
			BigDecimal transactionAmount = BigDecimalConverter.covertStringToBigDecimal(transactionRequest.getAmount());
			
			long transTimeStamp = DateConverter.converToTimeStamp(transactionRequest.getTimestamp());
			Instant transTime = DateConverter.converToTime(transactionRequest.getTimestamp());
			
			Duration d = Duration.between( currentTime , transTime);
			long diffSeconds = d.getSeconds();
			
			if(diffSeconds < 0 && diffSeconds > transTimeIn) {
				transactionService.addTransaction(transactionAmount, transTimeStamp);
				logger.info("End: postTransaction");
				return ResponseEntity.status(201).build();
			}else if(diffSeconds > 0){
				logger.error("Transaction Time is in future");
				throw new InvalidParseException("Transaction Time is in future");
			}else {
				logger.error("Transaction Is Older than 60 Seconds");
				throw new OldTransactionException("Transaction ss older than 60 Seconds");
			}
	}
	
	/*
	 * API to GET transactions of a MAP with in last 60 seconds of Current UTC time zone. 
	 */
	@GetMapping("/statistics")
	public ResponseEntity<Statistics> findStatistics() {
		logger.info("Begin: findStatistics");
		
		Statistics statistics = this.transactionService.findStatisticsOfTransaction();
		ResponseEntity<Statistics> response = new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
		
		logger.info("End: findStatistics");
		return response;
	}
	
	/*
	 * API to DELETE transactions of a MAP which are older than last 60 seconds of Current UTC time zone. 
	 */
	@DeleteMapping("/transactions")
	public ResponseEntity<Statistics> deleteStatistics() {
		logger.info("Begin: deleteStatistics");
		
		this.transactionService.deleteTransactions();
		
		logger.info("End: deleteStatistics");
		return ResponseEntity.status(204).build();
		
	}

}
