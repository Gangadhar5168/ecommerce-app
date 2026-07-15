package com.vg.mservices.orderservice.dto.request;

import com.vg.mservices.orderservice.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull(
            message = "Customer id should be valid"
    )
    private Long customerId;

    @NotBlank(
            message = "Product name should not be blank"
    )
    private String productName;

    @Positive(
            message = "Quantity should be greater than zero"
    )
    private Integer quantity;
}
