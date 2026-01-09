package com.ecom.app.repository;

import com.ecom.app.model.CartItem;
import com.ecom.app.model.Product;
import com.ecom.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    CartItem findByUsersAndProduct(Users users, Product product);

    void deleteByUsersAndProduct(Users users, Product product);
}
