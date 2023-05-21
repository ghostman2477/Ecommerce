package com.ghostman.ecommerce.fpl.repository;

import com.ghostman.ecommerce.fpl.model.Category;
import com.ghostman.ecommerce.fpl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Id(int id);
}