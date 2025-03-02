package com.quynhptt.java5.repository;

import com.quynhptt.java5.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategories_Name(String categoryName);

    Product findTopByOrderByIdDesc();

}
