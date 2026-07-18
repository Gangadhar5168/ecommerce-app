package com.vg.mservices.inventoryservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private Long id;
    private String productName;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
