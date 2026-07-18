package com.vg.mservices.inventoryservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInventoryRequest {
    private String productName;
    private Integer quantity;
}
