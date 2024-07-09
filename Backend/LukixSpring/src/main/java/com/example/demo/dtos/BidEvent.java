package com.example.demo.dtos;

import model.Event;

public class BidEvent {

	private Event event;
	private double currentBid;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public double getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
}
