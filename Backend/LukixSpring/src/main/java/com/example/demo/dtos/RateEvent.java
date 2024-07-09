package com.example.demo.dtos;

public class RateEvent {

	private Integer idUser;
	private Integer idEvent;
	private Integer rate;
	private String comment;
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Integer getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
}
