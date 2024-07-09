package com.example.demo.dtos;

public class Response {
	String email;
	String token;
	Boolean admin;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "Response [email=" + email + ", token=" + token + ", admin= "+admin+"]";
	}
	
	

}
