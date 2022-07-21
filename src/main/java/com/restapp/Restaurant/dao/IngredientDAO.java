package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {
}
