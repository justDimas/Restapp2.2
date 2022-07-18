package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<Dessert, Integer> {
}
