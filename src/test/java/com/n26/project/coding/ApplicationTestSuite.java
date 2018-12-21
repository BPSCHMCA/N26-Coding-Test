package com.n26.project.coding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.n26.project.coding.controller.TransactionController;
import com.n26.project.coding.service.TransactionService;

@RunWith(Suite.class)
@Suite.SuiteClasses({TransactionController.class, TransactionService.class})
@SpringBootTest
public class ApplicationTestSuite {

	@Test
	public void contextLoads() {
	}

}

