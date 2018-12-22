package com.n26.project.coding.controllerIT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.project.coding.Application;
import com.n26.project.coding.entities.Statistics;
import com.n26.project.coding.entities.Transactions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerIT {
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void postTransaction() {
		
		Instant currentTime = Instant.now();
		currentTime = currentTime.minusMillis(100);

		Transactions transaction = new Transactions("12.98434343433",currentTime.toString());

		HttpEntity<Transactions> entity = new HttpEntity<Transactions>(transaction, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/transactions"),
				HttpMethod.POST, entity, String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));

	}
	
	@Test
	public void findStatistics() {
		
			HttpEntity<Statistics> entity = new HttpEntity<Statistics>(null, headers);

			ResponseEntity<String> response = restTemplate.exchange(
					createURLWithPort("/statistics"),
					HttpMethod.GET, entity, String.class);

	        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		
	}
	
	@Test
	public void deleteStatistics() {
		
			HttpEntity<Statistics> entity = new HttpEntity<Statistics>(null, headers);

			ResponseEntity<String> response = restTemplate.exchange(
					createURLWithPort("/transactions"),
					HttpMethod.DELETE, entity, String.class);

	        assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
		
	}

}
