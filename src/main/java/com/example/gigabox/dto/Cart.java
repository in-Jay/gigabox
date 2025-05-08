package com.example.gigabox.dto;


import com.example.gigabox.users.GigaUser;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private GigaUser user;  // 사용자가 장바구니를 소유

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> items= new ArrayList<>();  // 여러 개의 상품 항목

    public void addItem(CartItem item) {
        item.setCart(this);  // CartItem에 Cart를 설정
        this.items.add(item);
    }
}
