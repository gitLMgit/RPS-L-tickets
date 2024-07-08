package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Category;
import com.example.demo.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository cr;
	
	public List<Category> getCategories(){
		return cr.findAll();	}
	
	public Boolean addCategory(String name) {
		Category newCat = new Category();
		newCat.setCategory(name);
		Integer idCat = cr.save(newCat).getIdCategory();
		if (idCat > 0)
			return true;
		return false;
	}
	
	public Boolean existCat(String name) {
		if (cr.findByCategory(name) != null)
			return true;
		return false;
	}
}
