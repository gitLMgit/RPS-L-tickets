package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.EventDTO;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.LocationRepository;

import model.Event;
import model.Location;

@Service
public class LocationService {

	
	public List<Location> getAll(){
		return locRep.findAll();
	}
}
