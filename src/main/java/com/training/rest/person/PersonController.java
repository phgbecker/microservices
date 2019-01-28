package com.training.rest.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/")
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.training.rest.person" })
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "person/{id}")
	public Person doGet(@PathVariable("id") Integer id) {
		return service.retrievePersonByID(id);
	}

	@PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer doPost(@RequestBody Person person) {
		return service.savePerson(person);
	}

	@PutMapping(value = "person/{id}")
	public Person doPut(@PathVariable("id") Integer id, @RequestBody Person person) {
		return service.savePerson(id, person);
	}

	@DeleteMapping(value = "person/{id}")
	public Integer doDelete(@PathVariable("id") Integer id) {
		return service.deletePerson(id);
	}

}
