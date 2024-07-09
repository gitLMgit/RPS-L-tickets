package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.BidEvent;
import com.example.demo.dtos.BidTicketRequest;
import com.example.demo.dtos.EventDTO;
import com.example.demo.dtos.RateEvent;
import com.example.demo.services.EventService;

@RestController
@RequestMapping("event/")
public class EventController {

	@Autowired
	EventService service;
	
	@GetMapping("getEvent/{eventId}")
	public ResponseEntity<?> getEvent(@PathVariable Integer eventId){
		BidEvent bidEvent = new BidEvent();
		bidEvent.setEvent(service.getEvent(eventId));
		bidEvent.setCurrentBid(service.getCurrBid(eventId));
		return ResponseEntity.ok(bidEvent);
	}
	
	@PostMapping("bidTicket")
	public ResponseEntity<?> bidTicket(@RequestBody BidTicketRequest bid) {
		System.out.println(bid.getIdUser());
		return ResponseEntity.ok(service.bidTicket(bid.getIdUser(), bid.getIdEvent(), bid.getBidPrice(), bid.getBidDateTime()));
	}
	
	@PostMapping("rateEvent")
	public ResponseEntity<?> rateEvent(@RequestBody RateEvent rate){
		return ResponseEntity.ok(!service.rateEvent(rate.getIdUser(), rate.getIdEvent(), rate.getRate(), rate.getComment()));
	}
	
	@PostMapping("addEvent")
	public ResponseEntity<?> addEvent(@RequestBody EventDTO dto){
		System.out.println(dto);
		return ResponseEntity.ok(service.addEvent(dto));
	}
}
