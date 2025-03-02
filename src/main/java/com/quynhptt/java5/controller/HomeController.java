package com.quynhptt.java5.controller;

import com.quynhptt.java5.entity.Cart;
import com.quynhptt.java5.entity.Product;
import com.quynhptt.java5.service.CartService;
import com.quynhptt.java5.service.ProductService;
import com.quynhptt.java5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        if (userDetails != null) {
            Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
            List<Cart> cardItems = cartService.getCartItems(userId);
            model.addAttribute("cartItems", cardItems);
            model.addAttribute("totalPrice", cardItems.stream().mapToDouble(it -> it.getQuantity() * it.getProduct().getPrice()).sum());
        }
        return "home";
    }
}
