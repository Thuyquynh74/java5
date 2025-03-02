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

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategories_Name(categoryName);
    }

    public void saveProduct(Product product) {
        if (product.getId() == null) {
            Product maxId = productRepository.findTopByOrderByIdDesc();
            product.setId(maxId.getId() + 1);
        }

        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }
}
