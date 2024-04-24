package com.example.hellospringboot.repositories;

import com.example.hellospringboot.models.Category;
import com.example.hellospringboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Category save(Category category);
    //@Query(value =  "select * from Category")
    List<Category> findAll();
//    List<Product> findAllByCategory_Id(Long id);
}
