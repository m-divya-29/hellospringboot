package com.example.hellospringboot.controllers;

import com.example.hellospringboot.models
        .Category;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<ResponseEntity<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }



}
