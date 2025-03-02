package com.quynhptt.java5.repository;

import com.quynhptt.java5.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    Cart findByUserIdAndProduct_Id(Long userId, Integer productId);

    Integer deleteByUserIdAndProduct_Id(Long userId, Integer productId);
}
