package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDAO extends JpaRepository<Property, Integer> {
}
