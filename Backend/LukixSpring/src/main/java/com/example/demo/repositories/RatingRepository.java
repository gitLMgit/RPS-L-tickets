package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

	@Query("select count(r) from Rating r where r.event.idEvent = :id")
	int getNumRates(@Param("id")Integer id);
	
	@Query("select avg(r.rate) from Rating r where r.event.idEvent = :id")
	int getAvgRates(@Param("id")Integer id);
	
	@Query("select r from Rating r where r.event.idEvent = :id")
	List<Rating> existRates(@Param("id")Integer id);
	
	
}
