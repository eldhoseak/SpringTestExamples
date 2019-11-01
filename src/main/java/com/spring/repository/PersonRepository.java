package com.spring.repository;


import com.spring.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
}