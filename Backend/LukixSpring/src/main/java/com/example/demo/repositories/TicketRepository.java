package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
