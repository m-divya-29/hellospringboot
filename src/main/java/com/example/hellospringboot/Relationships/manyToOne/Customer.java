package com.example.hellospringboot.Relationships.manyToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "rel_customer")
public class Customer {
    @Id
    Long customer_id;

    //FK: customer_id will be created on Order table.
    @OneToMany(mappedBy = "customer") //relationship mapped by column 'customer' in Order table. - if not, mapping table b/w order and customer will be created.
    List<Order> order;
}
