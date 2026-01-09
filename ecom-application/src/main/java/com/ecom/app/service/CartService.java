package com.ecom.app.service;

import com.ecom.app.dto.CartItemRequest;
import com.ecom.app.model.CartItem;
import com.ecom.app.model.Product;
import com.ecom.app.model.Users;
import com.ecom.app.repository.CartItemRepository;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;


    public boolean addToCart(String userId, CartItemRequest request) {

        Optional<Product> productOpt = productRepository.findById(request.getProductId());

        if(productOpt.isEmpty()){
            return  false;
        }
        Product product = productOpt.get();
        if(product.getStockQuantity()<request.getQuantity()){
            return false;
        }
        Optional<Users> userOpt = userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty()){
            return false;
        }

        Users users = userOpt.get();

        CartItem existingCartItem=cartItemRepository.findByUsersAndProduct(users,product);

        if(existingCartItem!=null){
            existingCartItem.setQuantity(existingCartItem.getQuantity()+ request.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }else{
            CartItem cartItem = new CartItem();
            cartItem.setUsers(users);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItem.setQuantity(request.getQuantity());
            cartItemRepository.save(cartItem);

        }

        return true;
    }
}
