package com.victor.microservice.resttesting;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.rest.client.RESTHelper;
import com.training.rest.person.Person;
import com.training.rest.person.PersonController;
import com.training.rest.person.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PersonController.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestTestingApplicationTests {

	private RESTHelper restHelper = new RESTHelper();

	@Before
	public void prepareData() {
		PersonService.cleanRepository();
		// adding two persons
		restHelper.postCall("Maria", 18);
		restHelper.postCall("Jose", 21);
	}

	@Test
	public void testAddingPerson() {
		Response response = restHelper.postCall("Joao", 23);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("3", response.readEntity(Integer.class).toString());
	}

	@Test
	public void testRemovePerson1() {
		Response response = restHelper.deleteCall(1);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("1", response.readEntity(Integer.class).toString());
	}

	@Test
	public void testRemovePerson2() {
		Response response = restHelper.deleteCall(2);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("2", response.readEntity(Integer.class).toString());
	}

	@Test
	public void testRetrievePerson1() {
		Response response = restHelper.getCall(1);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Person person = (Person) response.readEntity(Person.class);
		assertEquals("Maria", person.getName());
		assertEquals(18, person.getAge().intValue());
	}

	@Test
	public void testRetrievePerson2() {
		Response response = restHelper.getCall(2);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Person person = (Person) response.readEntity(Person.class);
		assertEquals("Jose", person.getName());
		assertEquals(21, person.getAge().intValue());
	}

	@Test
	public void testUpdatePerson1() {
		Response response = restHelper.putCall(1, "Maria Antonia", 56);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Person person = (Person) response.readEntity(Person.class);
		assertEquals("Maria Antonia", person.getName());
		assertEquals(56, person.getAge().intValue());
	}

	@Test
	public void testUpdatePerson2() {
		Response response = restHelper.putCall(1, "Jose Antonio", 55);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		Person person = (Person) response.readEntity(Person.class);
		assertEquals("Jose Antonio", person.getName());
		assertEquals(55, person.getAge().intValue());
	}

}
