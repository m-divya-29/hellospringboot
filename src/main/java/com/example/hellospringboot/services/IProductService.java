package com.example.hellospringboot.services;

import com.example.hellospringboot.models.Product;

public interface IProductService {
    Product getProductById(Long id);
}
