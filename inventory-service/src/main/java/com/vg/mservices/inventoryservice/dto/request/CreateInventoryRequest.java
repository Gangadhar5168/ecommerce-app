package com.vg.mservices.inventoryservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryRequest {
    private String productName;
    private Integer availableQuantity;
}
