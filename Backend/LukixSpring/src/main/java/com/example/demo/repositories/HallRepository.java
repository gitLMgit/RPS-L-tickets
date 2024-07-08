package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {

}
