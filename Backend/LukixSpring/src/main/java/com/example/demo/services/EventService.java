package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.EventDTO;
import com.example.demo.dtos.StatEvents;
import com.example.demo.dtos.StatRate;
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
	
	public Event getEvent(Integer id) {
		return er.findById(id).get();
	}
	
	public double getCurrBid(Integer id) {
		Currentbid cb = curbidr.getCurrBidForEvent(id);
		return cb == null ? tr.getStartPrice(id): cb.getCurrMaxValue();
	}
	
	public Boolean addEvent(EventDTO nEv) {
		try {
			Event newEvent = new Event();
			newEvent.setDate(nEv.getDate());
			newEvent.setCategory(catRepo.findById(nEv.getCategory()).get());
			newEvent.setLocation(locRep.findById(nEv.getLocation()).get());
			newEvent.setDescription(nEv.getDescription());
			newEvent.setTitle(nEv.getTitle());
			er.save(newEvent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean bidTicket(Integer idUser, Integer idEvent, Float bidPrice, Date bidDateTime) {
		Bid newBid = new Bid();
		newBid.setBidTime(bidDateTime);
		newBid.setBidValue(bidPrice);
		newBid.setBuyer(br.findById(idUser).get());
		newBid.setTicket(tr.findByEvent(idEvent).get(0));
		try {
			Bid savedBid = bidr.save(newBid);
			Currentbid currBid = curbidr.getCurrBidForEvent(idEvent);
			if (currBid == null)
				currBid = new Currentbid();
			currBid.setCurrMaxValue(bidPrice);
			currBid.setBid(savedBid);
			curbidr.save(currBid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean rateEvent(Integer idUser, Integer idEvent, Integer rate, String comment) {
		try {
			Rating newRating = new Rating();
			newRating.setBuyer(br.findById(idUser).get());
			newRating.setEvent(er.findById(idEvent).get());
			newRating.setRate(rate);
			newRating.setComment(comment);
			rr.save(newRating);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public List<StatEvents> getTicketsStat(){
		List<StatEvents> stat = new ArrayList<StatEvents>();
		List<Event> events = er.findAll();
		for (Event e: events) {
			StatEvents newStat = new StatEvents();
			newStat.setEvent(e);
			newStat.setSoldTickets(tr.getNumtickets(e.getIdEvent()));
			if (tr.existTickets(e.getIdEvent()).size() > 0)
				newStat.setRevenue(tr.getRevenur(e.getIdEvent()));
			else
				newStat.setRevenue(0);
			stat.add(newStat);
		}
		return stat;
	}
	
	public List<StatRate> getRatedEventsStat(){
		List<StatRate> stat = new ArrayList<StatRate>();
		List<Event> events = er.findAll();
		for (Event e: events) {
			StatRate newStat = new StatRate();
			newStat.setEvent(e);
			newStat.setNumRate(rr.getNumRates(e.getIdEvent()));
			if (rr.existRates(e.getIdEvent()).size() > 0)
				newStat.setAverageRate(rr.getAvgRates(e.getIdEvent()));
			else
				newStat.setAverageRate(0);
			stat.add(newStat);
		}
		return stat;
	}
}
