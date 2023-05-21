package com.ghostman.ecommerce.fpl.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //Join two column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category; // Passing object , we cannot directly handle the object in view, so we need dto

    private Double price;
    private Double weight;
    private String description;
    private String imageName; // only image name sent to database, image kept in different folder

}
