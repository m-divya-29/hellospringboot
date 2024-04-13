package com.example.hellospringboot.services;

import com.example.hellospringboot.dtos.FakeProductDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id) throws ProductNotExistsException;
    Product addNewProduct(Product product);
    List<Product> getAllProducts();
}
