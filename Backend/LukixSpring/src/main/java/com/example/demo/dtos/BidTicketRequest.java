package com.example.demo.dtos;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BidTicketRequest {
    private Integer idUser;
    private Integer idEvent;
    private Float bidPrice;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bidDateTime;
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
	public Float getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Float bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Date getBidDateTime() {
		return bidDateTime;
	}
	public void setBidDateTime(Date bidDateTime) {
		this.bidDateTime = bidDateTime;
	}

    // Getters and setters
}
