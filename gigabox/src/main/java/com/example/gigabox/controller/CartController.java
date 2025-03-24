package com.example.gigabox.controller;


import com.example.gigabox.dto.Cart;
import com.example.gigabox.dto.CartItem;
import com.example.gigabox.service.CartService;
import com.example.gigabox.service.UserService;
import com.example.gigabox.users.GigaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/cart")
@Controller
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    // 장바구니에 상품 추가
    @PostMapping("/add/{id}")
    @ResponseBody
    public ResponseEntity<String> addToCart(@PathVariable("id") Long detailId, Principal principal) {
        // 로그인된 사용자 정보 가져오기
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 장바구니에 추가할 수 있습니다.");
        }

        String username = principal.getName();  // 로그인된 사용자 이름 가져오기
        GigaUser loggedInUser = userService.getUsername(username);  // 사용자 정보 조회

        // 사용자의 장바구니에 상품 추가
        boolean added = cartService.addToCart(loggedInUser.getId(), detailId);

        if (added) {
            return ResponseEntity.ok("장바구니에 추가되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 추가에 실패했습니다. 다시 시도해 주세요.");
        }
    }


    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        // 로그인된 사용자 정보 가져오기
        if (principal == null) {
            return "redirect:/login";  // 로그인 페이지로 리디렉션
        }

        String username = principal.getName();
        GigaUser loggedInUser = userService.getUsername(username);

        // 해당 사용자의 장바구니 가져오기
        Cart cart = cartService.getCartByUserId(loggedInUser.getId());
        if (cart == null) {
            cart = new Cart();  // 장바구니가 없다면 새로 생성
        }

        // Cart 안에 있는 CartItem들 가져오기
        List<CartItem> cartItems = cart.getItems() != null ? cart.getItems() : new ArrayList<>(); // null 체크하여 안전하게 처리

        // 총 금액 계산 (가격 * 수량 + 배송비)
        int totalPrice = cartItems.stream()
                .mapToInt(cartItem -> cartItem.getLprice() * cartItem.getQuantity())  // 각 항목의 가격 * 수량 + 배송비 계산
                .sum();

        // 장바구니 항목과 총 금액을 모델에 추가
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart/cart";  // cart.html 페이지로 이동
    }


    @PostMapping("/update/{cartId}/{cartItemId}")
    @ResponseBody
    public ResponseEntity<?> updateCartQuantity(@PathVariable("cartId") Long cartId,
                                                @PathVariable("cartItemId") Long cartItemId,
                                                @RequestBody Map<String, Integer> requestData) {
        int quantity = requestData.get("quantity");
        System.out.println("Received quantity: " + quantity);

        try {
            // 서비스에서 장바구니 항목의 수량 업데이트 처리
            cartService.updateCartQuantity(cartId, cartItemId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();  // 에러 메시지 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 수량 업데이트 실패");
        }
    }


    // 장바구니 항목 삭제
    @PostMapping("delete/{cartId}")
    @ResponseBody
    public String deleteCart(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
        return "장바구니에서 삭제되었습니다.";
    }
}