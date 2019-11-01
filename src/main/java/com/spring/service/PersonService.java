package com.spring.service;


import com.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.repository.PersonRepository;

import java.util.List;


@Service
public class PersonService {

	@Autowired
	PersonRepository<Person> personRepository;

	@Transactional
	public List<Person> getAllPersons() {
		return (List<Person>) personRepository.findAll();
	}

	@Transactional

	@Cacheable("person")
	public List<Person> findByName(String name) {

		System.out.println("inside findByName method");
		return personRepository.findByFirstName(name);
	}

	@Transactional

	@CachePut(value="addresses")
	public boolean addPerson(Person person) {
		return personRepository.save(person) != null;
	}

	@Transactional
	@CacheEvict(value="person", allEntries=true)
	public boolean updatePerson(Person person) {
		return personRepository.save(person) != null;
	}
}
