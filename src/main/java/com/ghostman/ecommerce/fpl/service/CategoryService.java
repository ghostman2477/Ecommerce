package com.ghostman.ecommerce.fpl.service;

import com.ghostman.ecommerce.fpl.model.Category;
import com.ghostman.ecommerce.fpl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    //Save mthod to add category
    public void addCategory(Category category) {
        categoryRepository.save(category); // Add this object
    }

    //Retrieve category
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
