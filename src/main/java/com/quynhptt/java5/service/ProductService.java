package com.quynhptt.java5.service;

import com.quynhptt.java5.entity.Product;
import com.quynhptt.java5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }
}
