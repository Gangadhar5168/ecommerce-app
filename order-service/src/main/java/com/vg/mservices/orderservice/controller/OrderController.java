package com.vg.mservices.orderservice.controller;

import com.vg.mservices.orderservice.dto.request.OrderRequest;
import com.vg.mservices.orderservice.dto.response.OrderDetailsResponse;
import com.vg.mservices.orderservice.dto.response.OrderResponse;
import com.vg.mservices.orderservice.entity.Order;
import com.vg.mservices.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsResponse> getOrderById(@PathVariable Long id){
        OrderDetailsResponse orderDetails = orderService.getOrderById(id);

        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailsResponse>> getAllOrders(){
        List<OrderDetailsResponse> ordersList = orderService.getAllOrders();
        return ResponseEntity.ok(ordersList);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDetailsResponse> cancelOrder(@PathVariable Long id){
       OrderDetailsResponse orderDetails =  orderService.cancelOrder(id);
        return ResponseEntity.ok(orderDetails);
    }
}
