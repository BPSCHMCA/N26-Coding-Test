package com.n26.project.coding.service;

import java.math.BigDecimal;

import com.n26.project.coding.entities.Statistics;

public interface TransactionService {
	
	public void addTransaction(BigDecimal transAmount, long transTimeStamp);
	
	public Statistics findStatisticsOfTransaction();
	
	public void deleteTransactions();
	
}
