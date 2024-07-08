package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.EventDTO;
import com.example.demo.repositories.BidRepository;
import com.example.demo.repositories.BuyerRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CurrentBidRepository;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.LocationRepository;
import com.example.demo.repositories.RatingRepository;
import com.example.demo.repositories.TicketRepository;

import model.Bid;
import model.Currentbid;
import model.Event;
import model.Rating;
import model.Ticket;

@Service
public class EventService {

	@Autowired
	EventRepository er;

	@Autowired
	BuyerRepository br;

	@Autowired
	TicketRepository tr;

	@Autowired
	BidRepository bidr;

	@Autowired
	CurrentBidRepository curbidr;

	@Autowired
	RatingRepository rr;
	
	@Autowired
	LocationRepository locRep;
	
	@Autowired
	CategoryRepository catRepo;

	public List<Event> getAllEvent() {
		return er.findAll();
	}

	public List<Event> getEventByCat(Integer idC) {
		return er.findByCat(idC);
	}

	public List<Event> getEventByNameOrLocation(String input) {
		return er.findByTitleOrLocation(input);
	}
	
	public Boolean addEvent(EventDTO nEv) {
		try {
			Event newEvent = new Event();
			newEvent.setDate(nEv.getDate());
			newEvent.setCategory(catRepo.findById(nEv.getCategory()).get());
			newEvent.setLocation(locRep.findById(nEv.getLocation()).get());
			newEvent.setDescription(nEv.getDescription());
			newEvent.setTitle(nEv.getTitle());	
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean bidTicket(Integer idUser, Integer idTicket, Float bidPrice, Date bidDateTime) {
		Bid newBid = new Bid();
		newBid.setBidTime(bidDateTime);
		newBid.setBidValue(bidPrice);
		newBid.setBuyer(br.findById(idUser).get());
		newBid.setTicket(tr.findById(idTicket).get());
		try {
			Bid savedBid = bidr.save(newBid);
			Currentbid currBid = curbidr.getCurrentBid(idTicket);
			currBid.setCurrMaxValue(bidPrice);
			currBid.setBid(savedBid);
			curbidr.save(currBid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean rateEvent(Integer idUser, Integer idEvent, Integer rate) {
		try {
			Rating newRating = new Rating();
			newRating.setBuyer(br.findById(idUser).get());
			newRating.setEvent(er.findById(idEvent).get());
			newRating.setRate(rate);
			rr.save(newRating);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public List<Ticket> getTicketsStat(){
		return tr.getTicketStat();
	}
	
	public List<Event> getRatedEventsStat(){
		return er.getRatedEventStatistic();
	}
}
