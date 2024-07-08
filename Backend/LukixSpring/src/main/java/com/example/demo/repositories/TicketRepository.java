package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("select t from Ticket t inner join t.bids bid inner join bid.currentbids cbid"
			+ "  where bid.buyer.user_idUser = :idU")
	List<Ticket> getUserTickets(@Param("idU")Integer id);
	
	@Query("select t from Ticket t group by t.event.idEvent")
	List<Ticket> getTicketStat();
	
}
