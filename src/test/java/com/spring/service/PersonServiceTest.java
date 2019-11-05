package com.spring.service;

import com.spring.model.Person;
import com.spring.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonServiceTest {


    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testFindByName(){

        when(personRepository.findByFirstName(any())).thenReturn(Arrays.asList(new Person(1l,34, "Mark","Hall")));

        List<Person> persons =  personService.findByName("Mark");
        Assert.assertEquals("Mark", persons.get(0).getFirstName());
    }


}

/**
 To check the Service class, we need to have an instance of Service class created and available as a @Bean so that we can @Autowire it in our test class.
 This configuration is achieved by using the @TestConfiguration annotation.
 */