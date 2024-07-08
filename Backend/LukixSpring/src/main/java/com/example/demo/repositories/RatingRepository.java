package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
