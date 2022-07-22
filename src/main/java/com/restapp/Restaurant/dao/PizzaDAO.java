package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaDAO extends JpaRepository<Pizza, Integer> {
}
