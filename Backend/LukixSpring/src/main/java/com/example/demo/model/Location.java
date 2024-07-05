package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLocation;

	private String address;

	private int hasHall;

	private String name;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="location")
	@JsonBackReference
	private List<Event> events;

	//bi-directional many-to-one association to Hall
	@OneToMany(mappedBy="location")
	@JsonBackReference
	private List<Hall> halls;

	public Location() {
	}

	public int getIdLocation() {
		return this.idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHasHall() {
		return this.hasHall;
	}

	public void setHasHall(int hasHall) {
		this.hasHall = hasHall;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setLocation(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setLocation(null);

		return event;
	}

	public List<Hall> getHalls() {
		return this.halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public Hall addHall(Hall hall) {
		getHalls().add(hall);
		hall.setLocation(this);

		return hall;
	}

	public Hall removeHall(Hall hall) {
		getHalls().remove(hall);
		hall.setLocation(null);

		return hall;
	}

}