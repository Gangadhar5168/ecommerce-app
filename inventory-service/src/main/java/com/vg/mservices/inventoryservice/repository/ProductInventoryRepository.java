package com.vg.mservices.inventoryservice.repository;

import com.vg.mservices.inventoryservice.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Long> {
    Optional<ProductInventory> findByProductName(String productName);
}
