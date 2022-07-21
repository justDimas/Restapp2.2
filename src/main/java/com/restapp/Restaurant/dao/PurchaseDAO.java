package com.restapp.Restaurant.dao;

import com.restapp.Restaurant.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDAO extends JpaRepository<Purchase, Integer> {
}
