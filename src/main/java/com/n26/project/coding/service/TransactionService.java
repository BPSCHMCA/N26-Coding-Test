package com.n26.project.coding.service;

import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;

public interface TransactionService {
	
	public void addTransaction(Transactions transactionRequest);
	
	public Statistics findStatisticsOfTransaction();
	
	public void deleteTransactions();
	
}
