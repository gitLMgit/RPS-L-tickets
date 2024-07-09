package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Currentbid;

public interface CurrentBidRepository extends JpaRepository<Currentbid, Integer> {

	@Query("select c from Currentbid c where c.bid.ticket.idTicket = :idT")
	Currentbid getCurrentBid(@Param("idT") Integer idT);
	
	@Query("select c from Currentbid c where c.bid.idBid = :idB")
	List<Currentbid> getCurrBids(@Param("idB")Integer bid);
	
	@Query("select c from Currentbid c where c.bid.ticket.event.idEvent = :idE")
	Currentbid getCurrBidForEvent(@Param("idE")Integer bid);
}
