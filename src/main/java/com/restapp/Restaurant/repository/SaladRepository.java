package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Salad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaladRepository extends JpaRepository<Salad, Integer> {
}
