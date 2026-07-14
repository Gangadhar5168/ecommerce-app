package com.vg.mservices.orderservice.dto.request;

import com.vg.mservices.orderservice.entity.OrderStatus;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private String productName;
    private Integer quantity;
}
