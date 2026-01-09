package com.ecom.app.dto;

import com.ecom.app.model.Product;
import com.ecom.app.model.Users;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private Users users;
    private Product product;
    private BigDecimal price;
    private Integer quantity;



}
