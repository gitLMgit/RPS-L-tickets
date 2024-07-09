package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.services.CategoryService;
import com.example.demo.services.EventService;
import com.example.demo.services.LocationService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("permitAll/")
public class PermitAllController {

	
	@Autowired
	UserService serviceUsr;
	
	@Autowired
	CategoryService serviceCat;
	
	@Autowired
	EventService serviceEv;
	
	@Autowired
	LocationService serviceLoc;

	@GetMapping("getLocations")
	public ResponseEntity<?> getAllLoc(){
		return ResponseEntity.ok(serviceLoc.getAll());
	}
	
	@GetMapping("getAllEvent")
	public ResponseEntity<?> getAllEv(){
		return ResponseEntity.ok(serviceEv.getAllEvent());
	}
	
	@GetMapping("getEventByCat")
	public ResponseEntity<?> getEventByCat(@RequestParam("idC")Integer idCat){
		if (serviceEv.getEventByCat(idCat).size() == 0)
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(serviceEv.getEventByCat(idCat));
	}
	
	@GetMapping("getEventByNameOrLocation")
	public ResponseEntity<?> getEventByNameOrLocation(@RequestParam("inputString")String input){
		if (serviceEv.getEventByNameOrLocation(input).size() == 0)
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(serviceEv.getEventByNameOrLocation(input));
	}
	
	@GetMapping("getAllCategories")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(serviceCat.getCategories());
	}
	
	@GetMapping("existCategory")
	public ResponseEntity<?> findCategory(@RequestParam("catName")String name){
		return ResponseEntity.ok(serviceCat.existCat(name));
	}
	
	
	@PostMapping("register")
	public ResponseEntity<?> addCategory(UserDTO  dto){
		System.out.println(dto);
		if (serviceUsr.existUsername(dto.getUsername()))
			return ResponseEntity.ok(false);
		return ResponseEntity.ok(serviceUsr.register(dto));
	}
}
