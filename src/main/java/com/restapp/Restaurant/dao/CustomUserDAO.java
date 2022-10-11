package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserDAO extends JpaRepository<CustomUser, Integer> {
    Optional<CustomUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
