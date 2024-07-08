package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;

import model.User;

@Service
public class UserDetailProvider implements UserDetailsService {

	@Autowired
	UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> k = Optional.of(ur.findByUsername(username));
		
		k.orElseThrow(() -> new UsernameNotFoundException("Nije pronadjeno korisnicko ime: " + username));
		
		return k.map(MyUserDetails::new).get();
	}
}