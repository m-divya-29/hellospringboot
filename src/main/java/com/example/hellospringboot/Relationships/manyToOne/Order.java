package com.example.hellospringboot.Relationships.manyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "rel_order")
public class Order {
    @Id
    Long orderId;

    @ManyToOne //if not, mapping table b/w order and customer will be created.
    Customer customer;
}
