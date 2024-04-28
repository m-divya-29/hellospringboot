package com.example.hellospringboot.Relationships.oneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "rel_profile")
public class Profile {
    @Id
    Long profileId;
}
