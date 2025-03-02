package com.quynhptt.java5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private Long userId;
    private int quantity = 0;

}
