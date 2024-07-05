package com.example.demo.restControllers.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.category.CategoryService;

@RestController
@RequestMapping("category/")
public class CategoryController {

	
	@Autowired
	CategoryService service;
	
	@GetMapping("getCategories")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getCategories());
	}
}
