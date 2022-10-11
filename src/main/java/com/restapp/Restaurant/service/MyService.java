package com.restapp.Restaurant.service;

import java.util.List;
import java.util.NoSuchElementException;

public interface MyService<T> {
    T getById(T entity) throws NoSuchElementException;
    List<T> getAll();
    boolean add(T entity);
    boolean delete(T entity);
    boolean update(T entity);
}
