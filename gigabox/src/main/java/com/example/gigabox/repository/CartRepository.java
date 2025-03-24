package com.example.gigabox.repository;

import com.example.gigabox.dto.Cart;
import com.example.gigabox.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}