package com.quynhptt.java5.controller;

import com.quynhptt.java5.entity.Cart;
import com.quynhptt.java5.service.CartService;
import com.quynhptt.java5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;


    @GetMapping
    public String showCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        List<Cart> carts = cartService.getCartItems(userId);
        model.addAttribute("cartItems", carts);
        model.addAttribute("totalPrice", calculateTotalPrice(carts));
        return "cart";

    }

    private Double calculateTotalPrice(List<Cart> carts) {
        double totalPrice = 0.0;
        for (Cart cart : carts) {
            totalPrice = totalPrice + cart.getProduct().getPrice() * cart.getQuantity();
        }
        return totalPrice;
    }

    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.addToCart(productId, userId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.removeFromCart(productId, userId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId,
                             @RequestParam("quantity") int quantity) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.updateCart(productId, quantity, userId);
        return "redirect:/cart";
    }
}

