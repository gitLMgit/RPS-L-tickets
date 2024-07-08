package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EventService;

@RestController
@RequestMapping("statistic/")
public class StatisticController {

	@Autowired
	EventService evService;
	
	@GetMapping("ticketStat")
	public ResponseEntity<?> getTicketsStat(){
		return ResponseEntity.ok(evService.getTicketsStat());
	}
	
	@GetMapping("ratedEventsStat")
	public ResponseEntity<?> getRatedEvents(){
		return ResponseEntity.ok(evService.getRatedEventsStat());
	}
}
