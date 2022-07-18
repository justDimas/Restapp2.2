package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Integer> {
}
