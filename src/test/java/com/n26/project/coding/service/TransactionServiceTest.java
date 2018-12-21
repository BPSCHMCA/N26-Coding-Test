/*package com.n26.project.coding.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;

import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {
	
	@Autowired
	private TransactionService transactionService;
	
	@Value("${transaction.timeIn}")
	private Long transTimeIn;
	
	@Autowired
	private MockMvc mockMvc;
	
	private void addTestTransaction(Transactions transactionRequest) throws Exception{
        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transactionRequest))
        ).andExpect(status().is(201));
    }
	
	@Before
    public void setUp() throws Exception {
		Instant instant = Instant.now();
        long timeNow = instant.toEpochMilli();

        addTestTransaction(new Transactions(50.0,timeNow)); //Min
        for(int i=0;i<8;i++){
            addTestTransaction(new Transactions(100.0,timeNow+1+i));
        }

        addTestTransaction(new Transactions(150.0,timeNow+20)); //Max
    }
	
	@Test
    public void deleteTransactions() throws Exception {
        transactionService.deleteTransactions();
    }
	
	@Test
	public void findStatisticsOfTransaction() {
		Statistics statistic = transactionService.findStatisticsOfTransaction();
		assertEquals(150,statistic.getMax(),0);
	}

}
*/