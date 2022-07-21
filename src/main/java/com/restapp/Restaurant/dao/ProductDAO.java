package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
