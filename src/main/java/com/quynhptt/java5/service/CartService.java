package com.quynhptt.java5.service;

import com.quynhptt.java5.entity.Cart;
import com.quynhptt.java5.entity.Product;
import com.quynhptt.java5.repository.CartRepository;
import com.quynhptt.java5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public void addToCart(Integer productId, Long userId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(it -> {
            Cart cart = cartRepository.findByUserIdAndProduct_Id(userId, productId);
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(userId);
                cart.setProduct(product.get());
                cart.setQuantity(1);
                cartRepository.save(cart);
            } else {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.save(cart);
            }
        });
    }

    @Transactional
    public void removeFromCart(Integer productId, Long userId) {
        cartRepository.deleteCartByUserIdAndProductId(userId, productId);
    }

    public void updateCart(Integer productId, int quantity, Long userId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(it -> {
            Cart cart = cartRepository.findByUserIdAndProduct_Id(userId, productId);
            if (cart == null) {
                cart = new Cart();
                cart.setUserId(userId);
                cart.setProduct(product.get());
                cart.setQuantity(quantity);
                cartRepository.save(cart);
            } else {
                cart.setQuantity(cart.getQuantity() + quantity);
                cartRepository.save(cart);
            }
        });
    }

    @Transactional
    public void checkOut(Long userId) {
        cartRepository.deleteCartByUserId(userId);
    }
}

