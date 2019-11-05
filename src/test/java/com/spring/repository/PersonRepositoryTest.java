package com.spring.repository;

import com.spring.model.Person;
import com.spring.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testfindByFirstName(){

    this.entityManager.persist(new Person(34, "Mark","Hall"));
    Person person = (Person) personRepository.findByFirstName("Mark").get(0);

    Assert.assertEquals("Mark", person.getFirstName());

}


}
