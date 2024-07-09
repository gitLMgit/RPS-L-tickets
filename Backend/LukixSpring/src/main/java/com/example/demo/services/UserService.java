package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.BidRepository;
import com.example.demo.repositories.BuyerRepository;
import com.example.demo.repositories.CurrentBidRepository;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.repositories.UserRepository;

import model.Bid;
import model.Buyer;
import model.Currentbid;
import model.Ticket;
import model.User;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	BuyerRepository br;
	
	@Autowired
	TicketRepository tr;
	
	@Autowired
	BidRepository bidR;
	
	@Autowired
	CurrentBidRepository curBR;
	
	public User findByUsername(String email) {
		return ur.findByUsername(email);
	}
	
	public boolean register(UserDTO user) {
		User newUser = new User();
		newUser.setGender(user.getGender());
		newUser.setName(user.getName());
		newUser.setSurname(user.getSurname());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		Integer idUser = ur.save(newUser).getIdUser();
		Buyer b = new Buyer();
		b.setAge(user.getAge());
		b.setStatus(user.getStatus());
		b.setUser_idUser(idUser);
		Integer idB = br.save(b).getUser_idUser();
		if (idB > 0)
			return true;
		return false;
	}
	
	public boolean existUsername(String username) {
		if (ur.findByUsername(username) != null)
			return true;
		return false;
	}
	
	public Boolean updateProfile(UserDTO user, Integer userId) {
		try {
			User currUser = ur.findById(userId).get();
			currUser.setGender(user.getGender());
			currUser.setName(user.getName());
			currUser.setSurname(user.getSurname());
			currUser.setUsername(user.getUsername());
			currUser.setPassword(user.getPassword());
			Integer idUser = ur.save(currUser).getIdUser();
			Buyer currBuyer = br.findById(idUser).get();
			currBuyer.setAge(user.getAge());
			currBuyer.setStatus(user.getStatus());
			currBuyer.setUser_idUser(idUser);
			br.save(currBuyer);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Ticket> getUserTickets(Integer id){
		return tr.getUserTickets(id);
	}
	
	public List<Bid> getUserBids(Integer id){
		return bidR.getBids(id);
	}
	
	public Boolean removeBid(Integer user, Integer idBid) {
		Bid removeBid = bidR.findBidByUser(user, idBid);
		List<Currentbid> lista = curBR.getCurrBids(idBid);
		if ( lista.size() != 0) {
			curBR.delete(lista.get(0));
			bidR.delete(removeBid);
			return true;
		}
		return false;
	}
}
