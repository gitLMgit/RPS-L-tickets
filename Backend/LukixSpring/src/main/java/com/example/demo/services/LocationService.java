package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.LocationRepository;

import model.Event;
import model.Location;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locRep;

	
	public List<Location> getAll(){
		return locRep.findAll();
	}
}
