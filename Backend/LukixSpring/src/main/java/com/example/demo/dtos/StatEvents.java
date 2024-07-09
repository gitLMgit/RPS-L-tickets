package com.example.demo.dtos;

import model.Event;

public class StatEvents {
	
	private Event event;
	private int soldTickets;
	private double revenue;
	
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public int getSoldTickets() {
		return soldTickets;
	}
	public void setSoldTickets(int soldTickets) {
		this.soldTickets = soldTickets;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}


}
