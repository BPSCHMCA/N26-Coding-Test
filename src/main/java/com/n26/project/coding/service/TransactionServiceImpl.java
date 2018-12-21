package com.n26.project.coding.service;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;

/**
 * 
 * @author RajeswariT
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService{
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Value("${transaction.timeIn}")
	private int transTimeIn;
	
    private ConcurrentHashMap<Long,Double> transactionsData;
    
    public TransactionServiceImpl(){
    	this.transactionsData = new ConcurrentHashMap<>();
    }
	
	@Override
	public synchronized void addTransaction(Transactions transactionRequest) {
			try {
				long requestTimeStamp = transactionRequest.getTimestamp();
				
				String doubleVal = transactionRequest.getAmount();
				  BigDecimal bdTest = new BigDecimal(  doubleVal);
				  bdTest = bdTest.setScale(2, BigDecimal.ROUND_HALF_UP);
				  System.out.println("doubleVal::: "+doubleVal);
				//O(1)
				this.transactionsData.put(requestTimeStamp, transactionRequest.getAmount());
				
			}catch(Exception e) {
				logger.error("Exception in addTransaction: ", e);
			}
	}

	/**
	 * Get all statistics
	 */
	@Override
	public synchronized Statistics findStatisticsOfTransaction() {
		Statistics transStat = new Statistics();
		try {
			DoubleSummaryStatistics stats = this.transactionsData.values().stream().collect(
	                DoubleSummaryStatistics::new,
	                DoubleSummaryStatistics::accept,
	                DoubleSummaryStatistics::combine);
	        
	        transStat.setAvg(stats.getAverage());
	        transStat.setCount(stats.getCount());
	        transStat.setMax(stats.getMax());
	        transStat.setSum(stats.getSum());
	        transStat.setMin(stats.getMin());
		}catch(Exception e) {
			logger.error("Exception in findStatisticsOfTransaction: ", e);
		}
	    return transStat;
	}
	
	/**
	 * 
	 * Remove expired statistics older than 60sec
	 * 
	 * Time complexity: O(statTimestampsData.size() * log statTimestampsData.size()) -> O(1)
	 * 
	 */
	public synchronized void deleteTransactions() {
		System.out.println("this.transactionsData::: "+this.transactionsData.size());
		if(this.transactionsData != null && this.transactionsData.size() > 0)
			this.transactionsData = new ConcurrentHashMap<>();
	}
}
