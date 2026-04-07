package com.ecommerce.order.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private String userId;
    private String productId;
    private BigDecimal price;
    private Integer quantity;



}
