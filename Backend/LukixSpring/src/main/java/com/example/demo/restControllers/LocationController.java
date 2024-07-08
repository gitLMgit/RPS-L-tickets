package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.LocationService;

@RestController
@RequestMapping("location/")
public class LocationController {
	
	@Autowired
	LocationService service;

	@GetMapping("getLocations")
	public ResponseEntity<?> getAllLoc(){
		return ResponseEntity.ok(service.getAll());
	}
}
