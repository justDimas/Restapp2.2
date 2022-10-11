package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}