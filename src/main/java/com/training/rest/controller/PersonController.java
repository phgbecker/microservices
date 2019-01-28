package com.training.rest.controller;

import java.util.Map;

import javax.validation.Valid;

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

import com.training.rest.entity.Person;
import com.training.rest.service.IService;

@RestController
@RequestMapping("v1/")
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.training.rest" })
public class PersonController implements IController<Person, Integer> {

	@Autowired
	private IService<Person, Integer> service;

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
	@PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer doPost(@Valid @RequestBody Person person) {
		return service.save(person);
	}

	@Override
	@PutMapping(value = "person/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person doPut(@PathVariable("id") Integer id, @Valid @RequestBody Person person) {
		return service.save(id, person);
	}

	@Override
	@DeleteMapping(value = "person/{id}")
	public Integer doDelete(@PathVariable("id") Integer id) {
		return service.delete(id);
	}

}
