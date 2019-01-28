package com.training.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.rest.entity.Person;

/**
 * Classe para ajuda a consumir serviços REST
 *
 * @author victorvieira
 *
 */
public class RESTHelper {

	private static final String API_GET_ENDPOINT = "http://localhost:8082/v1/person/{id}";

	private static final String API_POST_ENDPOINT = "http://localhost:8082/v1/person";

	private static final String API_DELETE_ENDPOINT = "http://localhost:8082/v1/person/{id}";

	private static final String API_PUT_ENDPOINT = "http://localhost:8082/v1/person/{id}";

	public Response postCall(String name, Integer age) {
		Person person = new Person(name, age);
		Client client = ClientBuilder.newClient();
		return client.target(API_POST_ENDPOINT).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(person, MediaType.APPLICATION_JSON));
	}

	public Response getCall(Integer id) {
		Client client = ClientBuilder.newClient();
		return client.target(API_GET_ENDPOINT.replace("{id}", id.toString())).request(MediaType.APPLICATION_JSON).get();
	}

	public Response deleteCall(Integer id) {
		Client client = ClientBuilder.newClient();
		return client.target(API_DELETE_ENDPOINT.replace("{id}", id.toString())).request(MediaType.APPLICATION_JSON)
				.delete();
	}

	public Response putCall(Integer id, String name, Integer age) {
		Person person = new Person(name, age);
		Client client = ClientBuilder.newClient();
		return client.target(API_PUT_ENDPOINT.replace("{id}", id.toString())).request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(person, MediaType.APPLICATION_JSON));
	}

}
