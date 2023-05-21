package com.ghostman.ecommerce.fpl.controller;

import com.ghostman.ecommerce.fpl.service.CategoryService;
import com.ghostman.ecommerce.fpl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/category/{id}") // save id using path variable
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategory(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}") // save id using path variable
    public String viewProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProductById(id).get());
        // using get() because getProductById is returning optional, so get() to retrieve all products
        return "viewProduct";
    }
}
