package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the hall database table.
 * 
 */
@Entity
@NamedQuery(name="Hall.findAll", query="SELECT h FROM Hall h")
public class Hall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHall;

	private String name;

	private int seatsNumber;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JsonManagedReference
	private Location location;

	//bi-directional many-to-one association to SeatInHall
	@OneToMany(mappedBy="hall")
	@JsonBackReference
	private List<SeatInHall> seatInHalls;

	public Hall() {
	}

	public int getIdHall() {
		return this.idHall;
	}

	public void setIdHall(int idHall) {
		this.idHall = idHall;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatsNumber() {
		return this.seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<SeatInHall> getSeatInHalls() {
		return this.seatInHalls;
	}

	public void setSeatInHalls(List<SeatInHall> seatInHalls) {
		this.seatInHalls = seatInHalls;
	}

	public SeatInHall addSeatInHall(SeatInHall seatInHall) {
		getSeatInHalls().add(seatInHall);
		seatInHall.setHall(this);

		return seatInHall;
	}

	public SeatInHall removeSeatInHall(SeatInHall seatInHall) {
		getSeatInHalls().remove(seatInHall);
		seatInHall.setHall(null);

		return seatInHall;
	}

}