package com.training.rest.person;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestService<T> {

	Map<Integer, T> doGet();

	T doGet(@PathVariable("id") Integer id);

	Integer doPost(@RequestBody T entity);

	T doPut(@PathVariable("id") Integer id, @RequestBody T entity);

	Integer doDelete(@PathVariable("id") Integer id);

}
