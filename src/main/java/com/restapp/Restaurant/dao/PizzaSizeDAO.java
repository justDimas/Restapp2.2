package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaSizeDAO extends JpaRepository<PizzaSize, Integer> {
}
