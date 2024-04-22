package com.example.hellospringboot.services;

import com.example.hellospringboot.dtos.ProductDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.repositories.ICategoryRepository;
import com.example.hellospringboot.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements IProductService{

    IProductRepository productRepository;
    ICategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotExistsException {
        Optional<Product> foundProduct = productRepository.findById(id);
        if(foundProduct.isEmpty()) {
            throw new ProductNotExistsException("Product not found! ID: " + id);
        }
        return new ResponseEntity<>(foundProduct.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> addNewProduct(Product product) {
        Optional<Category> category = categoryRepository.findByName(product.getCategory().getName());
        if(category.isEmpty()) {
            Category newCategory = categoryRepository.save(product.getCategory());
            product.setCategory(newCategory);
        } else {
            product.setCategory(category.get());
        }
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Override
    public List<ResponseEntity<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ResponseEntity<Product>> responses = new ArrayList<>();
        for(Product product : products) {
            responses.add(new ResponseEntity<>(product, HttpStatus.OK));
        }
        return responses;
    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotExistsException {
        return null;
    }

    @Override
    public ResponseEntity<Product> replaceProductById(Long id, ProductDTO product) throws ProductNotExistsException {
        return null;
    }
}
