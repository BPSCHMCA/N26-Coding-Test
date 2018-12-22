package com.n26.project.coding.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.n26.project.coding.entities.Transactions;

import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TransactionController.class)
public class TransactionControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void postTransaction() throws Exception {
		
        Transactions transaction = new Transactions("12.98434343433","2018-12-22T09:21:36.109Z");
        Transactions transactionOld = new Transactions("12.98434343433","2018-12-22T09:21:36.109Z");

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transaction))
        ).andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transactionOld))
        ).andExpect(status().is(204));

    }
	
	@Test
    public void findStatistics() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform(get("/statistics")).andReturn();
			   
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
    }
	
	@Test
	public void deleteStatistics() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(delete("/transactions")).andReturn();
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(204, status);
	}
}
