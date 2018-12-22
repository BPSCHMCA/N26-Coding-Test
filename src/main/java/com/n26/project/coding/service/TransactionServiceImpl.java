package com.n26.project.coding.service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.n26.project.coding.entities.Statistics;

/**
 * 
 * @author RajeswariT
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService{
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Value("${transaction.timeInSeconds}")
	private int transTimeIn;
	
    private ConcurrentHashMap<Long,BigDecimal> transactionsData;
    
    public TransactionServiceImpl(){
    	this.transactionsData = new ConcurrentHashMap<>();
    }
    
    /**
     * Add requested transactions under 60 seconds from current UTC Time Zone.
     * Since ConcurrentHashMap(putIfAbsent) used to handle transaction requests, 
     * so that concurrency & thread safety can be provided to all transactions.
     * ConcurrentHashMap (key = timestamp in milli seconds). each request is unique enough to add transactions.
     * ConcurrentHashMap runs in constant time and memory: O(1) for put method.
     */
	@Override
	public void addTransaction(BigDecimal transAmount, long transTimeStamp) {
		logger.info("Begin: TransactionServiceImpl:addTransaction");
			try {
				this.transactionsData.putIfAbsent(transTimeStamp, transAmount); //check-if-absent-then-set method
			}catch(Exception e) {
				logger.error("Exception in TransactionServiceImpl:addTransaction: ", e);
			}
		logger.info("End: TransactionServiceImpl:addTransaction");
	}

	/**
	 * ConcurrentHashMap runs in constant time and memory: O(1) for get method.
	 * Here all map values are taken single time to prepare Statistics.
	 * Eclipse collection used for capturing statistics of BigDecimal Values.
	 */
	@Override
	public Statistics findStatisticsOfTransaction() {
		logger.info("Begin: TransactionServiceImpl:findStatisticsOfTransaction");
		Statistics transStat = new Statistics();
		try {
			BigDecimalSummaryStatistics stats = this.transactionsData.values().stream().collect(
					Collectors2.summarizingBigDecimal(e -> e.setScale(2, BigDecimal.ROUND_HALF_UP))); //BigDecimal rounded up to 2 fractions.
	        
	        transStat.setAvg(stats.getAverage());
	        transStat.setCount(stats.getCount());
	        transStat.setMax(stats.getMax());
	        transStat.setSum(stats.getSum());
	        transStat.setMin(stats.getMin());
		}catch(Exception e) {
			logger.error("Exception in TransactionServiceImpl:findStatisticsOfTransaction: ", e);
		}
		logger.info("End: TransactionServiceImpl:findStatisticsOfTransaction");
	    return transStat;
	}
	
	/**
	 * 
	 * Remove existing transactions.
	 * 
	 */
	public void deleteTransactions() {
		logger.info("Begin: TransactionServiceImpl:deleteTransactions");
		try {
			if(this.transactionsData != null && this.transactionsData.size() > 0) {
				this.transactionsData = new ConcurrentHashMap<>(); // Initializing back
			}
		}catch(Exception e) {
			logger.error("Exception in TransactionServiceImpl:deleteTransactions: ", e);
		}
		
		logger.info("End: TransactionServiceImpl:deleteTransactions");
	}
}
