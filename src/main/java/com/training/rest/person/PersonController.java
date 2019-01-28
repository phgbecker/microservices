package com.training.rest.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/")
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.training.rest.person" })
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping(method = RequestMethod.GET, value = "person/{id}")
	public Person doPost(@PathVariable("id") Integer id) {
		// TODO: Implement here
		return null;
	}

	@RequestMapping(value = "person", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer doPost(@RequestBody Person person) {
		return service.savePerson(person);
	}

	// TODO: Implement put and delete

}
