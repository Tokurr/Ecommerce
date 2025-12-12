package com.app.ecom.controller;


import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<String> addCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request
    ){
        if(!cartService.addToCart(userId,request))
        {
            return ResponseEntity.badRequest().body("product out of stock or usr not found or product noy found");

        }
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Transactional
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(@RequestHeader("X-User-ID") String userId,@PathVariable String productId)
    {
        cartService.deleteItemCart(userId,productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CartItem>> getCardByUserId(@RequestHeader("X-User-Id") String userId)
    {
        return ResponseEntity.ok(cartService.getCardByUserId(userId));
    }

}
