package com.example.hellospringboot.services;

import ch.qos.logback.core.model.processor.ProcessorException;
import com.example.hellospringboot.dtos.FakeProductDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceFakeStore implements IProductService {

    public static final String FAKESTOREAPI_PRODUCTS = "https://fakestoreapi.com/products/";
    RestTemplate restTemplate;

    @Autowired
    ProductServiceFakeStore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertProductDTOToProduct(FakeProductDTO fakeProductDTO) {
        Product product = new Product();
        product.setId(fakeProductDTO.getId());
        product.setTitle(fakeProductDTO.getTitle());
        product.setDescription(fakeProductDTO.getDescription());
        product.setImageURL(fakeProductDTO.getImage());
        Category category = new Category();
        category.setName(fakeProductDTO.getCategory());
        product.setCategory(category);
        product.setPrice(fakeProductDTO.getPrice());
        return product;
    }

    private FakeProductDTO convertProductToFakeProductDto(Product product) {
        FakeProductDTO fakeProductDTO = new FakeProductDTO();
        fakeProductDTO.setCategory(product.getCategory().getName());
        fakeProductDTO.setTitle(product.getTitle());
        fakeProductDTO.setPrice(product.getPrice());
        fakeProductDTO.setDescription(product.getDescription());
        fakeProductDTO.setImage(product.getImageURL());
        return fakeProductDTO;
    }

    public Product getProductById(Long id) throws ProductNotExistsException {

        FakeProductDTO fakeProductDTO = restTemplate.getForObject(
                FAKESTOREAPI_PRODUCTS + id,
                FakeProductDTO.class);
        if(fakeProductDTO == null) {
            throw new ProductNotExistsException("Product with ID: " +id + " does not exist");
        }
        return convertProductDTOToProduct(fakeProductDTO);
    }

    public Product addNewProduct(Product product) {
       ResponseEntity<FakeProductDTO> fakeProductDTO = restTemplate.postForEntity(FAKESTOREAPI_PRODUCTS, convertProductToFakeProductDto(product), FakeProductDTO.class);
       return convertProductDTOToProduct(fakeProductDTO.getBody());
    }

    public List<Product> getAllProducts() {
        /* This does not work - java type erasure!
        List<FakeProductDTO> allProducts = restTemplate.getForObject(FAKESTOREAPI_PRODUCTS, List.class);
        List<Product> products = new ArrayList<>();
        for (FakeProductDTO fakeProductDTO : allProducts) {
            products.add(convertProductDTOToProduct(fakeProductDTO));
        }
         */
        FakeProductDTO[] productDTOS = restTemplate.getForObject(FAKESTOREAPI_PRODUCTS, FakeProductDTO[].class);
        List<Product> products = new ArrayList<>();
        for (FakeProductDTO fakeProductDTO : productDTOS) {
            products.add(convertProductDTOToProduct(fakeProductDTO));
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
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null, FakeProductDTO.class);
        HttpMessageConverterExtractor<FakeProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeProductDTO.class, restTemplate.getMessageConverters());
        FakeProductDTO fakeProductDTO = restTemplate.execute(FAKESTOREAPI_PRODUCTS + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertProductDTOToProduct(fakeProductDTO);
    }
}
