package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.CustomRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRoleDAO extends JpaRepository<CustomRole, Integer> {
}
