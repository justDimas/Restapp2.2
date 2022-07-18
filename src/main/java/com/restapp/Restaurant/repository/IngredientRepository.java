package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
