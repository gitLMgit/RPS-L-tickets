package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer> {

}
