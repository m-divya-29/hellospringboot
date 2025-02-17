package com.example.hellospringboot.controllers;

import com.example.hellospringboot.dtos.ExceptionDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    @Autowired
    ProductController( @Qualifier("selfProductService")IProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products
     * @return
     */
    @GetMapping("")
    public List<ResponseEntity<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Get product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotExistsException {
        return productService.getProductById(id);
    }

    /**
     * Add a new product
     */
    @PostMapping("/")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    /**
     * Delete a product.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return productService.deleteProductById(id);
    }

    /**
     * Update a product
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotExistsException {
       return productService.replaceProductById(id,product);
    }

    /**
     * Local ProductNotExistsException handler
     */
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistsException(ProductNotExistsException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Get products in a category.
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public List<ResponseEntity<Product>> getProductInCategory(@PathVariable Long id) {
        return productService.findProductInCategory(id);
    }
}
