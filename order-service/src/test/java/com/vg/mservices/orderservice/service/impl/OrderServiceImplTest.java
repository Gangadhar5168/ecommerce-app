package com.vg.mservices.orderservice.service.impl;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;
import com.vg.mservices.orderservice.entity.OrderStatus;
import com.vg.mservices.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldCreateOrderSuccessfully(){
        OrderRequest request  = OrderRequest.builder()
                .customerId(101L)
                .productName("Laptop")
                .quantity(1)
                .build();

        Order savedOrder = Order.builder()
                .id(1L)
                .customerId(101L)
                .productName("Laptop")
                .quantity(1)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.save(any(Order.class)))
                .thenReturn(savedOrder);

        OrderResponse response = orderService.createOrder(request);
        assertNotNull(response);
        assertEquals(1L,response.getOrderId());
        assertEquals(OrderStatus.CREATED,response.getStatus());
        verify(orderRepository).save(any(Order.class));

    }
}
