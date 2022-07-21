package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessDAO extends JpaRepository<Access, Integer> {
}
