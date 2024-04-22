package com.example.hellospringboot.services;

import com.example.hellospringboot.dtos.ProductDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import com.example.hellospringboot.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    public static final String FAKESTOREAPI_PRODUCTS = "https://fakestoreapi.com/products/";
    RestTemplate restTemplate;

    @Autowired
    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Product> getProductById(Long id) throws ProductNotExistsException {
        ProductDTO productDTO = restTemplate.getForObject(
                FAKESTOREAPI_PRODUCTS + id,
                ProductDTO.class);
        if(productDTO == null) {
            throw new ProductNotExistsException("Product with ID: " +id + " does not exist");
        }
        return new ResponseEntity<>(ProductUtils.convertProductDTOToProduct(productDTO), HttpStatus.OK);
    }

    public ResponseEntity<Product> addNewProduct(Product product) {
       ResponseEntity<ProductDTO> productDTO = restTemplate.postForEntity(FAKESTOREAPI_PRODUCTS, product, ProductDTO.class);
       return new ResponseEntity<>(ProductUtils.convertProductDTOToProduct(productDTO.getBody()), HttpStatus.CREATED);
    }

    public List<ResponseEntity<Product>> getAllProducts() {
        /* This does not work - java type erasure!
        List<FakeProductDTO> allProducts = restTemplate.getForObject(FAKESTOREAPI_PRODUCTS, List.class);
        List<Product> products = new ArrayList<>();
        for (FakeProductDTO fakeProductDTO : allProducts) {
            products.add(convertProductDTOToProduct(fakeProductDTO));
        }
         */
        ProductDTO[] productDTOS = restTemplate.getForObject(FAKESTOREAPI_PRODUCTS, ProductDTO[].class);
        List<ResponseEntity<Product>> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOS) {
            products.add(new ResponseEntity<>(ProductUtils.convertProductDTOToProduct(productDTO), HttpStatus.OK));
        }
        return products;
    }

    /**
     * not the final working code - TODO: pass/work with ProductDTOs.
     * @param id
     * @param product
     * @return
     */
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null, ProductDTO.class);
        HttpMessageConverterExtractor<ProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductDTO.class, restTemplate.getMessageConverters());
        ProductDTO productDTO = restTemplate.execute(FAKESTOREAPI_PRODUCTS + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return ProductUtils.convertProductDTOToProduct(productDTO);
    }

    /**
     * Delete a product and return the product object.
     */
    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotExistsException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null, ProductDTO.class);
        HttpMessageConverterExtractor<ProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductDTO.class, restTemplate.getMessageConverters());
        ProductDTO productDTO = restTemplate.execute(FAKESTOREAPI_PRODUCTS + id, HttpMethod.DELETE, requestCallback, responseExtractor);
        if(productDTO == null) {
            throw new ProductNotExistsException("Product with ID: " + id + " does not exist");
        }
        return new ResponseEntity<>(ProductUtils.convertProductDTOToProduct(productDTO), HttpStatus.OK);
    }

    /**
     * Update a product by replacing it.
     */
    @Override
    public ResponseEntity<Product> replaceProductById(Long id, ProductDTO product) throws ProductNotExistsException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, ProductDTO.class);
        HttpMessageConverterExtractor<ProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(ProductDTO.class, restTemplate.getMessageConverters());
        ProductDTO productDTO = restTemplate.execute(FAKESTOREAPI_PRODUCTS +id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(productDTO == null) {
            throw new ProductNotExistsException("Product with ID: " + id + " does not exist");
        }
        return new ResponseEntity<>(ProductUtils.convertProductDTOToProduct(productDTO), HttpStatus.OK);
    }

}
