package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PizzaDAO extends JpaRepository<Pizza, Integer> {
}
