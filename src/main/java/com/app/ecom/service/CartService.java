package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    public CartService(ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public boolean addToCart(String userId, CartItemRequest request) {

      Optional<Product> productOpt =  productRepository.findById(request.getProductId());

      if(productOpt.isEmpty())
      {
          return false;
      }

      Product product = productOpt.get();
      if(product.getStockQuantity()<request.getQuantity())
      {
          return false;
      }

      Optional<User> userOptional = userRepository.findById(userId);

      if(userOptional.isEmpty())
      {
          return false;

      }

      User user = userOptional.get();
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user,product);

        if(existingCartItem!=null)
        {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }
        else{
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;

    }

    public void deleteItemCart(String userId, String productId) {

        Product product = productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found by id: " + productId));
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found by id : " + userId));

        cartItemRepository.deleteByUserAndProduct(user,product);


    }

    public List<CartItem> getCardByUserId(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found by id: " + userId));
        return cartItemRepository.findByUser(user);
    }

}
