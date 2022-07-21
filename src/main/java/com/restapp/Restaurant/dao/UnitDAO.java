package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitDAO extends JpaRepository<Unit, Integer> {
}
