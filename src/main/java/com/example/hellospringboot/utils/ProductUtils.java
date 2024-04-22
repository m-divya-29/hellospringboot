package com.example.hellospringboot.utils;

import com.example.hellospringboot.dtos.ProductDTO;
import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;

public class ProductUtils {

    public static Product convertProductDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImage());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public static ProductDTO convertProductToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImageURL());
        return productDTO;
    }
}
