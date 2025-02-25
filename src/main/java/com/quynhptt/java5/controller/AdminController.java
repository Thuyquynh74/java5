package com.quynhptt.java5.controller;

import com.quynhptt.java5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final ProductService productService;

    @GetMapping("products")
    public String home(Model model) {
//        List<Product> products = productService.getProducts();
//        model.addAttribute("products", products);
        return "admin";
    }
}
