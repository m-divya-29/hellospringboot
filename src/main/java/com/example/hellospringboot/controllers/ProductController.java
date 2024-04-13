package com.example.hellospringboot.controllers;

import com.example.hellospringboot.dtos.ExceptionDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    @Autowired
    ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotExistsException {
        return productService.getProductById(id);
    }

    @PostMapping("")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

    }

    /**
     * Local ProductNotExistsException handler
     * @param e
     * @return
     */
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistsException(ProductNotExistsException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
