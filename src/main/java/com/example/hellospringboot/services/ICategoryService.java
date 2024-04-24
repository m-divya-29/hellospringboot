package com.example.hellospringboot.services;

import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    List<ResponseEntity<Category>> getAllCategories();

}
