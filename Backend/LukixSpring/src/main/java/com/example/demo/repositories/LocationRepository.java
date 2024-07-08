package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
