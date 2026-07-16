package com.vg.mservices.orderservice.dto.response;

import com.vg.mservices.orderservice.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsResponse {

        private Long id;
        private Long customerId;
        private String productName;
        private Integer quantity;
        private OrderStatus status;
        private LocalDateTime createdAt;
    }
