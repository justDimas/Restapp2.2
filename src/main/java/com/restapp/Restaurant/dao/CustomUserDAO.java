package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserDAO extends JpaRepository<CustomUser, Integer> {
    CustomUser findByUserName(String userName);
}
