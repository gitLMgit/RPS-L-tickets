package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query("select e from Event e where e.category.idCategory= :cat")
	List<Event> findByCat(@Param("cat")Integer cat);
	
	@Query("select e from Event e where e.title= :input or e.location.name= :input")
	List<Event> findByTitleOrLocation(@Param("input")String input);
	
	@Query("select e from Event e inner join e.ratings r")
	List<Event> getRatedEventStatistic();

}
