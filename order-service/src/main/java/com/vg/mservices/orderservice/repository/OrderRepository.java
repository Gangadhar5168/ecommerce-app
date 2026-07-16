package com.vg.mservices.orderservice.repository;

import com.vg.mservices.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
