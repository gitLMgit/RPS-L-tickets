package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

}
