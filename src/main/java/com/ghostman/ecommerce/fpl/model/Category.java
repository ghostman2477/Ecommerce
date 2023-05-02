package com.ghostman.ecommerce.fpl.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Entity // For database
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Jpa will manage the id in the database
    @Column(name="category_id")

    private int id;

    private String name;

}
