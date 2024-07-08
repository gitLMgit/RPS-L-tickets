package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.NewUserDTO;
import com.example.demo.repositories.BuyerRepository;
import com.example.demo.repositories.UserRepository;

import model.Buyer;
import model.User;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	BuyerRepository br;
	
	public boolean register(NewUserDTO user) {
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
}
