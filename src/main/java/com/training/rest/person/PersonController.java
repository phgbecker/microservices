package com.training.rest.person;

import java.util.Map;

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
public class PersonController implements RestService<Person> {

	@Autowired
	private PersonService service;

	@Override
	@GetMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Person> doGet() {
		return service.retrieveAll();
	}

	@Override
	@GetMapping(value = "person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person doGet(@PathVariable("id") Integer id) {
		return service.retrieveByID(id);
	}

	@Override
	@PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer doPost(@RequestBody Person person) {
		return service.save(person);
	}

	@Override
	@PutMapping(value = "person/{id}")
	public Person doPut(@PathVariable("id") Integer id, @RequestBody Person person) {
		return service.save(id, person);
	}

	@Override
	@DeleteMapping(value = "person/{id}")
	public Integer doDelete(@PathVariable("id") Integer id) {
		return service.delete(id);
	}

}
