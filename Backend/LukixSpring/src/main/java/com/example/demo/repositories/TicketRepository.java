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
	
	@Query("select t from Ticket t order by t.event.idEvent")
	List<Ticket> getTicketStat();
	
	@Query("select t from Ticket t where t.event.idEvent = :id")
	List<Ticket> findByEvent(@Param("id")Integer idE);
	
	@Query("select t.startPrice from Ticket t where t.event.idEvent = :id")
	double getStartPrice(@Param("id")Integer idE);
	
	@Query("select count(t) from Ticket t where t.event.idEvent = :id")
	int getNumtickets(@Param("id")Integer idE);
	
	@Query("select sum(t.startPrice) from Ticket t where t.event.idEvent = :id")
	double getRevenur(@Param("id")Integer idE);
	
	@Query("select t from Ticket t where t.event.idEvent = :id")
	List<Ticket> existTickets(@Param("id")Integer idE);
	
}
