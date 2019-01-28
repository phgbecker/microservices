package com.training.rest.service;

import java.util.Map;

public interface IService<T, I> {

	T retrieveByID(I id);

	Map<I, T> retrieveAll();

	Integer save(T entity);

	T save(I key, T entity);

	Integer delete(I id);

}
