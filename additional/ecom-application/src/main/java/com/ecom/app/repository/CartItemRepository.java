package com.ecom.app.repository;

import com.ecom.app.dto.CartItemResponse;
import com.ecom.app.model.CartItem;
import com.ecom.app.model.Product;
import com.ecom.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByUsersAndProduct(Users users, Product product);

    void deleteByUsersAndProduct(Users users, Product product);


    List<CartItem> findByUsers(Users users);

    void deleteByUsers(Users users);
}
