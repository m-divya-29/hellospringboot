package com.example.hellospringboot.repositories;

import com.example.hellospringboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Product save(Product product);
    List<Product> findAll();
    Product deleteProductById(Long id);
    List<Product> findByCategoryId(Long id);
}
