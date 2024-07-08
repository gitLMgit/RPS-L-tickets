package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer> {

	@Query("select b from Bid b inner join b.currentbids cb where b.buyer.user_idUser =:idU and cb.bid.idBid != b.idBid")
	List<Bid> getBids(@Param("idU")Integer id);
	
	@Query("select b from Bid b where b.buyer.user_idUser =:idU and b.idBid = :idB")
	Bid findBidByUser(@Param("idU")Integer id, @Param("idB")Integer bid);
}
