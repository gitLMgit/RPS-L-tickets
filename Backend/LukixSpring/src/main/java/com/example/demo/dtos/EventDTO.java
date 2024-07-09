package com.example.demo.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EventDTO {
	
	private String description;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date date;
	private String title;
	private Integer category;
	private Integer location;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "EventDTO [description=" + description + ", date=" + date + ", title=" + title + ", category=" + category
				+ ", location=" + location + "]";
	}
}
