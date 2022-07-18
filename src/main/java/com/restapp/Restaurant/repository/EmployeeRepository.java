package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
