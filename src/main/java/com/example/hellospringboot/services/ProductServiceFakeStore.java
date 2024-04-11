package com.example.hellospringboot.services;

import com.example.hellospringboot.dtos.ProductDTO;
import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceFakeStore implements IProductService {

    RestTemplate restTemplate;

    @Autowired
    ProductServiceFakeStore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertProductDTOToProduct(ProductDTO productDTO) {
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

    public Product getProductById(Long id) {
        ProductDTO productDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                ProductDTO.class);
        return convertProductDTOToProduct(productDTO);
    }
}
