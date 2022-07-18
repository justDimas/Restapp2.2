package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
}
