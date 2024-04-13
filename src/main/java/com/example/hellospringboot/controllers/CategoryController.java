package com.example.hellospringboot.controllers;

import com.example.hellospringboot.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("")
    public List<Category> getAllCategories() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Category getProductInCategory(@PathVariable Long id) {
        return new Category();
    }

}
