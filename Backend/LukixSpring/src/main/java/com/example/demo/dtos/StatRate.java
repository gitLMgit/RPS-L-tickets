package com.example.demo.dtos;

import model.Event;

public class StatRate {
	private Event event;
	private Integer numRate;
	private double averageRate;
		
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Integer getNumRate() {
		return numRate;
	}
	public void setNumRate(Integer numRate) {
		this.numRate = numRate;
	}
	public double getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}
}
