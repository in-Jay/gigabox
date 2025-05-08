package com.example.gigabox.repository;

import com.example.gigabox.dto.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
