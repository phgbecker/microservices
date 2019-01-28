package com.training.rest.person;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private static int sequence = 0;
	private static Map<Integer, Person> repository = new HashMap<>();
	
	public Person retrievePersonByID(Integer id) {
		return repository.get(id);
	}
	
	public Integer savePerson(Person person) {
		sequence++; //get next val
		repository.put(sequence, person);
		return sequence;
	}
	
	public Person savePerson(Integer key, Person person) {
		repository.put(key, person);
		return person;
	}
	
	public Integer deletePerson(Integer key) {
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
