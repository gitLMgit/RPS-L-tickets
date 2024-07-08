package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
