package com.example.demo.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("category/")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	
	@PostMapping("addCategory")
	public ResponseEntity<?> addCategory(@RequestParam("catName")String  catName){
		return ResponseEntity.ok(service.addCategory(catName));
	}
	

}
