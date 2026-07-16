package com.vg.mservices.orderservice.dto.response;

import com.vg.mservices.orderservice.entity.OrderStatus;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private OrderStatus status;
}
