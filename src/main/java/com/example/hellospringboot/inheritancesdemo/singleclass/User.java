package com.example.hellospringboot.inheritancesdemo.singleclass;

import jakarta.persistence.Id;

public class User {
    @Id
    private Long id;
    private String name;
    private String email;
}
