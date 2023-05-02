package com.ghostman.ecommerce.fpl.repository;

import com.ghostman.ecommerce.fpl.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // It will give all the methods to access to database
}
