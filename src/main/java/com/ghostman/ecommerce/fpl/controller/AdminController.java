//It will control all the admin related routes

package com.ghostman.ecommerce.fpl.controller;

import com.ghostman.ecommerce.fpl.dto.ProductDTO;
import com.ghostman.ecommerce.fpl.model.Category;
import com.ghostman.ecommerce.fpl.model.Product;
import com.ghostman.ecommerce.fpl.service.CategoryService;
import com.ghostman.ecommerce.fpl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") +
            "/src/main/resources/static/productImages"; // current dir in which application
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
        // Looping category and adding it to the row
    }

    @GetMapping("/admin/categories/add")
    public String getCategoriesAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) { //Take out obj whose name is category
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    //Update - for update we have to send object also to update
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategories(@PathVariable int id, Model model) {
        Optional<Category> category=categoryService.getCategoryById(id);
        if(category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    //---------Product Section ----------

    @GetMapping("/admin/products")
    public String getProduct(Model model) {
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        // We have to send all the categories also
        // because whatever cat are there in database it will only show in the options
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add") // It will run when we will click on the submit button
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {

        //We have to create a product object bcoz we are bringing productDTO
        //and we want to save a product
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());//we need to fetch the category by CategoryId and then we need to pass the obj
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());

        //Image - two half - one in database and another in images in folder

        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID=file.getOriginalFilename();

            //we now want to save this file in productimages folder
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes()); // getBytes bcoz at the end all are in binaries
        } else { // if file is empty - we are updating the product
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Product product=productService.getProductById(id).get();
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(productDTO.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);   

        return "productsAdd";
    }
}
