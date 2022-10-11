package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodDAO extends JpaRepository<Good, Integer> {
}
