package com.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "age")
	private Integer age;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;


	public Person(Long id,Integer age, String firstName, String lastName){
		this.id= id;
		this.age= age;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(Integer age, String firstName, String lastName){
		this.age= age;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(){

	}


	public Long getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
