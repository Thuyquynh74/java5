package com.quynhptt.java5.controller;

import com.quynhptt.java5.entity.Product;
import com.quynhptt.java5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String listProducts(@RequestParam(value = "category", required = false) String category, Model model) {
        if (category != null) {
            model.addAttribute("products", productService.getProductsByCategory(category));
        } else {
            model.addAttribute("products", productService.getProducts());
        }
        return "product-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}

