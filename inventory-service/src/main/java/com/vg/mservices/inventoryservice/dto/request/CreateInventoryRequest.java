package com.vg.mservices.inventoryservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryRequest {
    @NotBlank(
            message = "Product should not be blank"
    )
    private String productName;
    @Positive(
            message = "Quantity should be more than Zero"
    )
    private Integer availableQuantity;
}
