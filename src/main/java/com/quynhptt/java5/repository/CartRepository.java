package com.quynhptt.java5.repository;

import com.quynhptt.java5.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long userId);

    Cart findByUserIdAndProduct_Id(Long userId, Integer productId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userId = :userId AND c.product.id = :productId")
    void deleteCartByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Integer productId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userId = :userId")
    void deleteCartByUserId(@Param("userId") Long userId);
}
