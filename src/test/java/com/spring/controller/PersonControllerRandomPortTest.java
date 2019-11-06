package com.spring.controller;

import com.spring.model.Person;
import com.spring.service.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerRandomPortTest {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	PersonService personService;

	@Test
	public void testAllPersonsEndpoint() throws Exception {
		when(personService.getAllPersons()).thenReturn(Arrays.asList(new Person(1l,34, "Mark","Hall")));
		Assert.assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/person",
				String.class).contains("Mark"));
	}

	@Test
	public void testAddPersonsEndpoint() throws Exception {
		when(personService.updatePerson(any())).thenReturn(true);

		final String baseUrl = "http://localhost:"+port+"/person";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);


		HttpEntity<String> request = new HttpEntity<>( "{\"age\": 35,\"firstName\" : \"Yoav\",\"lastName\": \"Karan\"}", headers);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		//Verify request succeed
		Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
		Assert.assertEquals("\"CREATED\"", result.getBody());

		}
}

/**
 * start the application up and listen for a connection like it would do in production, and then send an HTTP request and assert the response.
 * Note the use of webEnvironment=RANDOM_PORT to start the server with a random port (useful to avoid conflicts in test environments),
 * and the injection of the port with @LocalServerPort. Also note that Spring Boot has provided a TestRestTemplate for you automatically,
 * and all you have to do is @Autowired it.
 */

