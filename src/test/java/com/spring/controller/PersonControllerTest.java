package com.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.model.Person;
import com.spring.service.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;


@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@MockBean
	PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAllPersonsEndpoint() throws Exception {
		when(personService.getAllPersons()).thenReturn(Arrays.asList(new Person(1l,34, "Mark","Hall")));
		this.mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testAddPersonsEndpoint() throws Exception {
		when(personService.addPerson(any())).thenReturn(true);
		String personJson = "{\"age\": 35,\"firstName\" : \"Yoav\",\"lastName\": \"Karan\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/person")
				.accept(MediaType.APPLICATION_JSON).content(personJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
		Assert.assertEquals("\"CREATED\"", response.getContentAsString());
		}
}

