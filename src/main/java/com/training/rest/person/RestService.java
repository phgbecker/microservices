package com.training.rest.person;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestService<T, I> {

	Map<Integer, T> doGet();

	T doGet(@PathVariable("id") I id);

	Integer doPost(@RequestBody T entity);

	T doPut(@PathVariable("id") I id, @RequestBody T entity);

	Integer doDelete(@PathVariable("id") I id);

}
