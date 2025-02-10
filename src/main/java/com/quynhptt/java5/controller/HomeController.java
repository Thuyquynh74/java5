package com.quynhptt.java5.controller;

import com.quynhptt.java5.entity.Product;
import com.quynhptt.java5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("")
    public String home(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "home";
    }
}
