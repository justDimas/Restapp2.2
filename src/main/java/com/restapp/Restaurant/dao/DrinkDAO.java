package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkDAO extends JpaRepository<Drink, Integer> {
}
