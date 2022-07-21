package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
}
