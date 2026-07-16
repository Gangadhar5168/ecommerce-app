package com.vg.mservices.orderservice.mapper;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;

public class OrderMapper {
    private OrderMapper(){}

    public static OrderDetailsResponse toOrderDetailsResponse(Order order){
        return OrderDetailsResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }

    public static Order toOrder(OrderRequest request){
        return Order.builder()
                .customerId(request.getCustomerId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .build();
    }
}
