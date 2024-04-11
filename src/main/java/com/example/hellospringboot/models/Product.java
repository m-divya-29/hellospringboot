package com.example.hellospringboot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private Category category;
    private String description;
    private String imageURL;
}
