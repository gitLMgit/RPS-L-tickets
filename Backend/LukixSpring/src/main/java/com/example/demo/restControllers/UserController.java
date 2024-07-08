package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("register")
	public ResponseEntity<?> addCategory(UserDTO  dto){
		System.out.println(dto);
		if (service.existUsername(dto.getUsername()))
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(service.register(dto));
	}
	
	@GetMapping("getUserTickets")
	public ResponseEntity<?> getTicketsForUser(Integer idUser){
		return ResponseEntity.ok(service.getUserTickets(idUser));
	}
	
	@GetMapping("getUserBids")
	public ResponseEntity<?> getUserBids(Integer idUser){
		return ResponseEntity.ok(service.getUserBids(idUser));
	}
	
	@GetMapping("removeBid")
	public ResponseEntity<?> removeBid(Integer idUser, Integer idBid){
		return ResponseEntity.ok(service.removeBid(idUser, idBid));
	}
}
