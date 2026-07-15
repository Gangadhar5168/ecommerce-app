package com.vg.mservices.orderservice.service;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderDetailsResponse getOrderById(Long id);
    List<OrderDetailsResponse> getAllOrders();
}
