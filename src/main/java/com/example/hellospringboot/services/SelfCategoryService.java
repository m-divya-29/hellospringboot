package com.example.hellospringboot.services;

import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelfCategoryService implements ICategoryService {
    ICategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<ResponseEntity<Category>> getAllCategories() {
        List<Category> foundCategories = categoryRepository.findAll();
        List<ResponseEntity<Category>> responses = new ArrayList<>();
        for (Category category : foundCategories) {
            responses.add(new ResponseEntity<>(category, HttpStatus.OK));
        }
        return responses;
    }


}
