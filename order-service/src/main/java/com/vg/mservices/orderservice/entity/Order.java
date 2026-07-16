package com.vg.mservices.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String productName;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime createdAt;
}
