package com.training.rest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.training.rest.entity.Person;

@Service
public class PersonServiceImpl implements IService<Person, Integer> {

	private static int sequence = 0;
	private static Map<Integer, Person> repository = new HashMap<>();

	@Override
	public Person retrieveByID(Integer id) {
		return repository.get(id);
	}

	@Override
	public Map<Integer, Person> retrieveAll() {
		return repository;
	}

	@Override
	public Integer save(Person person) {
		sequence++; //get next val
		repository.put(sequence, person);
		return sequence;
	}

	@Override
	public Person save(Integer key, Person person) {
		repository.put(key, person);
		return person;
	}

	@Override
	public Integer delete(Integer key) {
		repository.remove(key);
		return key;
	}

	/**
	 * Used for unit testing only
	 */
	public static void cleanRepository() {
		sequence = 0;
		repository.clear();
	}
}
