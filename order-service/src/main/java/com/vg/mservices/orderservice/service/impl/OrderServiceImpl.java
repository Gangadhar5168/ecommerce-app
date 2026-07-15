package com.vg.mservices.orderservice.service.impl;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;
import com.vg.mservices.orderservice.entity.OrderStatus;
import com.vg.mservices.orderservice.repository.OrderRepository;
import com.vg.mservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        Order savedOrder = orderRepository.save(order);
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(savedOrder.getId())
                .status(savedOrder.getStatus())
                .build();
        return orderResponse;
    }

    @Override
    public OrderDetailsResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        OrderDetailsResponse odr = OrderDetailsResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
        return odr;
    }

    @Override
    public List<OrderDetailsResponse> getAllOrders() {
        List<OrderDetailsResponse> ordersDetailsList = new ArrayList<>();
        List<Order> ordersList = orderRepository.findAll();
        for(Order order:ordersList){
            OrderDetailsResponse odr = OrderDetailsResponse.builder()
                    .id(order.getId())
                    .customerId(order.getCustomerId())
                    .productName(order.getProductName())
                    .quantity(order.getQuantity())
                    .status(order.getStatus())
                    .createdAt(order.getCreatedAt())
                    .build();
            ordersDetailsList.add(odr);
        }
        return ordersDetailsList;
    }


}
