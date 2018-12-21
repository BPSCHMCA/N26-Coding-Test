/*package com.n26.project.coding.controller;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.n26.project.coding.entities.Transactions;

import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	TransactionController transactionController;
	
	@Test
    public void postTransaction() throws Exception {
        Instant instant = Instant.now();
        long currentTimeStamp = instant.toEpochMilli();

        Transactions transaction = new Transactions(100.0,currentTimeStamp);
        Transactions transactionOld = new Transactions(100.0,currentTimeStamp-70000);

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transaction))
        ).andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transactionOld))
        ).andExpect(status().is(204));

        transaction = new Transactions(100.0,currentTimeStamp-10);
        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transaction))
        ).andExpect(status().is(201));

    }
	
	 @Test
	    public void stats() throws Exception {
	        this.mockMvc.perform(get("/statistics")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("")));
	    }
}
*/