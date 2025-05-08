package com.example.gigabox.service;

import com.example.gigabox.dto.Cart;
import com.example.gigabox.dto.CartItem;
import com.example.gigabox.dto.Item;
import com.example.gigabox.repository.CartItemRepository;
import com.example.gigabox.repository.CartRepository;
import com.example.gigabox.repository.ItemApiRepository;
import com.example.gigabox.repository.UserRepository;
import com.example.gigabox.users.GigaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemApiRepository itemApiRepository;
    private final UserRepository userRepository;

    // 장바구니에 상품을 추가하는 메서드
    public boolean addToCart(Long userId, Long itemId) {
        // 사용자의 장바구니를 가져옴
        Cart cart = cartRepository.findByUserId(userId);

        // 장바구니가 없으면 새로 생성
        if (cart == null) {
            cart = new Cart();
            // GigaUser 객체를 userId로부터 찾아서 설정
            GigaUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

            // GigaUser 객체를 Cart에 설정
            cart.setUser(user);
        }
        // 장바구니의 아이템 목록이 null이면 빈 리스트로 초기화
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        // 상품 정보 가져오기
        Item item = itemApiRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 기존 장바구니에 해당 상품이 있는지 확인
        CartItem existingItem = cart.getItems().stream()
                .filter(ci -> ci.getProductId().equals(item.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // 상품이 이미 장바구니에 있으면 수량을 증가시킴
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            // 장바구니에 없으면 새 항목 추가
            CartItem newCartItem = new CartItem();
            newCartItem.setProductId(item.getProductId());
            newCartItem.setTitle(item.getTitle());
            newCartItem.setLprice(item.getLprice());
            newCartItem.setQuantity(1);  // 새로 추가될 때는 수량 1
            newCartItem.setCategory(item.getCategory3());
            newCartItem.setImage(item.getImage());
            newCartItem.setCart(cart);  // Cart와 연결
            cart.addItem(newCartItem);  // Cart에 상품 항목 추가
        }

        // 장바구니 저장
        cartRepository.save(cart);
        return true;
    }

    // 장바구니 목록 보기
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public void updateCartQuantity(Long cartId, Long cartItemId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new IllegalArgumentException("CartItem not found"));

        cartItem.updateQuantity(quantity);
        cartItemRepository.save(cartItem);  // 수량 업데이트

        // 총 금액을 다시 계산하는 로직 필요
        updateTotalPrice(cart);  // 예시로 추가한 메서드, 실제 로직 구현 필요
    }

    public int updateTotalPrice(Cart cart) {
        // 장바구니 항목이 없으면 총 금액은 0으로 설정
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            return 0;
        }

        // 총 금액 계산
        int totalPrice = 0;

        // 각 CartItem에 대해 가격 * 수량 계산
        for (CartItem cartItem : cart.getItems()) {
            // 각 항목의 가격 * 수량
            int itemTotal = cartItem.getLprice() * cartItem.getQuantity();
            totalPrice += itemTotal;  // 각 항목의 금액을 더함
        }

        // 배송비를 더한 총 금액 계산 (예: 고정 배송비 3000원)
        int shippingFee = 3000; // 배송비 고정값
        totalPrice += shippingFee;

        // 총 금액을 반환
        return totalPrice;
    }

    // 장바구니 항목 삭제
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }


    // 사용자별 장바구니 가져오기
    public Cart getCartByUserId(Long userId) {
        // 해당 사용자에 해당하는 장바구니를 찾음
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(new GigaUser()); // 임시로 사용자 객체를 설정
        }
        return cart;
    }
}