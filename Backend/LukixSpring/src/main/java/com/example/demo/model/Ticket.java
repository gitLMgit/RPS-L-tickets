package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTicket;

	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	@Column(name="start_price")
	private double startPrice;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="ticket")
	@JsonBackReference
	private List<Bid> bids;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JsonManagedReference
	private Event event;

	//bi-directional many-to-one association to SeatInHall
	@ManyToOne
	@JoinColumn(name="Seat_in_Hall_idSeatHall")
	@JsonManagedReference
	private SeatInHall seatInHall;

	public Ticket() {
	}

	public int getIdTicket() {
		return this.idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getStartPrice() {
		return this.startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setTicket(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setTicket(null);

		return bid;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public SeatInHall getSeatInHall() {
		return this.seatInHall;
	}

	public void setSeatInHall(SeatInHall seatInHall) {
		this.seatInHall = seatInHall;
	}

}