package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.NewUserDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("register")
	public ResponseEntity<?> addCategory(NewUserDTO  dto){
		System.out.println(dto);
		if (service.existUsername(dto.getUsername()))
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(service.register(dto));
	}
}
