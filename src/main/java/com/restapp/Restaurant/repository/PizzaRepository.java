package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
