package com.quynhptt.java5.controller;

import com.quynhptt.java5.entity.Cart;
import com.quynhptt.java5.service.CartService;
import com.quynhptt.java5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        return "/cart::cart-content";

    }

    private Double calculateTotalPrice(List<Cart> carts) {
        double totalPrice = 0.0;
        for (Cart cart : carts) {
            totalPrice = totalPrice + cart.getProduct().getPrice() * cart.getQuantity();
        }
        return totalPrice;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.addToCart(productId, userId);
        return ResponseEntity.ok("Added to cart");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.removeFromCart(productId, userId);
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId,
                                             @RequestParam("quantity") int quantity) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.updateCart(productId, quantity, userId);
        return ResponseEntity.ok("Updated cart");
    }

    @PostMapping("/plus")
    public ResponseEntity<String> plusProduct(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.updateCart(productId, 1, userId);
        return ResponseEntity.ok("Updated cart");
    }


    @PostMapping("/minus")
    public ResponseEntity<String> minusProduct(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("productId") Integer productId) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.updateCart(productId, -1, userId);
        return ResponseEntity.ok("Updated cart");
    }


    @PostMapping("/check-out")
    public ResponseEntity<String> checkout(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.findUserByUsername(userDetails.getUsername()).getId();
        cartService.checkOut(userId);
        return ResponseEntity.ok("Checked out");
    }
}

