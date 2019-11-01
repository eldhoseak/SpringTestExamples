package com.spring.controller;

import com.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.spring.service.PersonService;

import java.util.List;


@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/personByName/{name}", method = RequestMethod.GET)
	public List<Person> getPersoneByName(@PathVariable String name) {
		return personService.findByName(name);
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public List<Person> getAll() {
		return personService.getAllPersons();
	}

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public HttpStatus insertPersone(@RequestBody Person person) {
		return personService.addPerson(person) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public HttpStatus updatePersone(@RequestBody Person person) {
		return personService.updatePerson(person) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}
}

