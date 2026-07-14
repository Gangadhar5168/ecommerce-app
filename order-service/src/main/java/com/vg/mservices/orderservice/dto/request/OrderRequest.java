package com.vg.mservices.orderservice.dto.request;

import com.vg.mservices.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private String productName;
    private Integer quantity;
}
