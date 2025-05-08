package com.example.gigabox.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")  // Cart와의 관계 설정
    private Cart cart;  // 하나의 Cart에 속하는 여러 CartItem

    private String productId;  // 상품 ID
    private String title;      // 상품 제목
    private int lprice;        // 상품 가격
    private int quantity;      // 상품 수량
    private String category;   // 상품 카테고리
    private String image;      // 상품 이미지

    // getProductId() 메서드가 있어야 함
    public String getProductId() {
        return productId;
    }

    // 수량이 변경될 때 가격 업데이트
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
