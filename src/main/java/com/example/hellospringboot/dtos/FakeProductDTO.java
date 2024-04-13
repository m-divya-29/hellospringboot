package com.example.hellospringboot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDTO {
    private Long id;
    private String title;
    private String category;
    private String description;
    private String image;
    private Double price;
}
