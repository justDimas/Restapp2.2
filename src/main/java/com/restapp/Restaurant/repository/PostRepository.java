package com.restapp.Restaurant.repository;

import com.restapp.Restaurant.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
