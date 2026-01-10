package com.ecom.app.service;


import com.ecom.app.dto.OrderItemDTO;
import com.ecom.app.dto.OrderResponse;
import com.ecom.app.model.*;
import com.ecom.app.repository.OrderRepository;
import com.ecom.app.repository.UserRepository;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Optional<OrderResponse> createOrder(String userId) {

        //Validate Cart iteem
        List<CartItem> cartitem =cartService.getCart(userId);
        if(cartitem.isEmpty()){
            return Optional.empty();
        }
        
        //Validate User
        Optional<Users> optionalUser = userRepository.findById(Long.valueOf(userId));
        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        Users users = optionalUser.get();

        //Calculate Total Price
        BigDecimal totalPrice = cartitem.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUsers(users);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        List<OrderItem> orderItems = cartitem.stream().map(item -> new OrderItem(
                null,
                item.getProduct(),
                item.getQuantity(),
                item.getPrice(),
                order
        )).toList();
        order.setItem(orderItems);
        Order saveOrder = orderRepository.save(order);

        //Clear Cart
        cartService.clearCart(userId);

                return Optional.of(mapToOrderResopnse(saveOrder));

    }

    private OrderResponse mapToOrderResopnse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItem().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        )).toList(),

                                order.getCreatedAt()
                        );
    }
}
