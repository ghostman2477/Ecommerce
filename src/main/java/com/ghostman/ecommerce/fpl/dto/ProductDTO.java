package com.ghostman.ecommerce.fpl.dto;

import com.ghostman.ecommerce.fpl.model.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDTO {

    // It will contain the same data which are there in products
    private Long id;
    private String name;
    private int categoryId;
    private Double price;
    private Double weight;
    private String description;
    private String imageName; // only image name sent to database, image kept in different folder

}
