package com.vg.mservices.orderservice.service.impl;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;
import com.vg.mservices.orderservice.entity.OrderStatus;
import com.vg.mservices.orderservice.exception.OrderAlreadyCancelledException;
import com.vg.mservices.orderservice.exception.OrderNotFoundException;
import com.vg.mservices.orderservice.mapper.OrderMapper;
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
        Order order = OrderMapper.toOrder(request);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderDetailsResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order not found with id : "+id));
        return OrderMapper.toOrderDetailsResponse(order);
    }

    @Override
    public List<OrderDetailsResponse> getAllOrders() {
        List<OrderDetailsResponse> ordersDetailsList = new ArrayList<>();
        List<Order> ordersList = orderRepository.findAll();
        for(Order order:ordersList){
            OrderDetailsResponse odr = OrderMapper.toOrderDetailsResponse(order);
            ordersDetailsList.add(odr);
        }
        return ordersDetailsList;
    }

    @Override
    public OrderDetailsResponse cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Cannot cancel order. Order not found with id :"+id));
        if(order.getStatus()==OrderStatus.CANCELLED){
            throw new OrderAlreadyCancelledException("Order already cancelled with order id :"+id);
        }
        order.setStatus(OrderStatus.CANCELLED);
       orderRepository.save(order);


       return OrderMapper.toOrderDetailsResponse(order);
    }


}
