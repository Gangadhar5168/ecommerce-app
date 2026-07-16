package com.vg.mservices.orderservice.service.impl;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
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
import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldReturnOrderByIdSuccessfully(){
        Order order = Order.builder()
                .id(1L)
                .customerId(101L)
                .productName("Laptop")
                .quantity(1)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        OrderDetailsResponse odr = orderService.getOrderById(1L);
        assertNotNull(odr);
        assertEquals(101L,odr.getCustomerId());
        assertEquals("Laptop",odr.getProductName());
        assertEquals(1,odr.getQuantity());
        assertEquals(OrderStatus.CREATED,odr.getStatus());
        verify(orderRepository).findById(1L);
    }

    @Test
    void shouldReturnAllOrdersSuccessfully(){
        Order order1 = Order.builder()
                .id(1L)
                .customerId(101L)
                .productName("Laptop")
                .quantity(1)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .customerId(102L)
                .productName("Mobile")
                .quantity(1)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findAll())
                .thenReturn(List.of(order1,order2));
        List<OrderDetailsResponse> odrList = orderService.getAllOrders();
        assertNotNull(odrList);
        assertEquals(2,odrList.size());

        assertEquals(1L,odrList.getFirst().getId());
        assertEquals("Laptop",odrList.getFirst().getProductName());

        assertEquals(2L, odrList.get(1).getId());
        assertEquals("Mobile",odrList.get(1).getProductName());

        verify(orderRepository).findAll();

    }

    @Test
    void shouldCancelOrderSuccessfully(){
        Order order = Order.builder()
                .id(1L)
                .customerId(101L)
                .productName("Laptop")
                .quantity(1)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        OrderDetailsResponse odr = orderService.cancelOrder(1L);
        assertEquals(1L,odr.getId());
        assertEquals(101L,odr.getCustomerId());
        assertEquals(OrderStatus.CANCELLED,odr.getStatus());
        verify(orderRepository).findById(1L);
        verify(orderRepository).save(any(Order.class));

    }
}
