package com.example.demo.dtos;

public class Response {
	Integer id;
	String token;
	Boolean admin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean role) {
		this.admin = role;
	}
	@Override
	public String toString() {
		return "Response [id=" + id + ", token=" + token + ", admin= "+admin+"]";
	}
	
	

}
