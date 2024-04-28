package com.example.hellospringboot.Relationships.oneToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "rel_user")
public class User {
    @Id
    Long userId;

    @OneToOne(cascade = CascadeType.ALL) //creates FK profile_id on User table.
    Profile userProfile;
}
