package com.example.hellospringboot.services;

import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    ResponseEntity<Product> getProductById(Long id) throws ProductNotExistsException;
    ResponseEntity<Product> addNewProduct(Product product);
    List<ResponseEntity<Product>> getAllProducts();
    ResponseEntity<Product> deleteProductById(Long id) throws ProductNotExistsException;
    ResponseEntity<Product> replaceProductById(Long id, Product product) throws ProductNotExistsException;
    List<ResponseEntity<Product>> findProductInCategory(Long id);
}
