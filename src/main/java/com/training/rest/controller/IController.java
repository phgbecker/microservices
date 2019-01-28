package com.training.rest.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IController<T, I> {

	Map<Integer, T> doGet();

	T doGet(@PathVariable("id") I id);

	Integer doPost(@Valid @RequestBody T entity);

	T doPut(@PathVariable("id") I id, @Valid @RequestBody T entity);

	Integer doDelete(@PathVariable("id") I id);

}
