package com.example.hellospringboot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    @ManyToOne
    private Category category;
    private String description;
    private String imageURL;
    private Double price;
}
