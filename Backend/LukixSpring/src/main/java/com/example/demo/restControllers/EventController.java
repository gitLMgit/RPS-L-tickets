package com.example.demo.restControllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EventService;

@RestController
@RequestMapping("event/")
public class EventController {

	@Autowired
	EventService service;
	
	@GetMapping("getAllEvent")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAllEvent());
	}
	
	@GetMapping("getEventByCat")
	public ResponseEntity<?> getEventByCat(@RequestParam("idC")Integer idCat){
		if (service.getEventByCat(idCat).size() == 0)
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(service.getEventByCat(idCat));
	}
	
	@GetMapping("getEventByNameOrLocation")
	public ResponseEntity<?> getEventByNameOrLocation(@RequestParam("inputString")String input){
		if (service.getEventByNameOrLocation(input).size() == 0)
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(service.getEventByNameOrLocation(input));
	}
	
	@PostMapping("bidTicket")
	public ResponseEntity<?> bidTicket(Integer idUser, Integer idTicket, Float bidPrice,  
			@DateTimeFormat(pattern="yyyy-MM-dd") Date bidDateTime) {
		return ResponseEntity.ok(service.bidTicket(idUser, idTicket, bidPrice, bidDateTime));
	}
	
	@PostMapping("rateEvent")
	public ResponseEntity<?> rateEvent(Integer idUser, Integer idEvent, Integer rate){
		return ResponseEntity.ok(!service.rateEvent(idUser, idEvent, rate));
	}
}
