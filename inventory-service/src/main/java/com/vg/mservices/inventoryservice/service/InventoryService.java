package com.vg.mservices.inventoryservice.service;

import com.vg.mservices.inventoryservice.dto.request.CreateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.request.UpdateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    InventoryResponse createInventory(CreateInventoryRequest request);

    InventoryResponse getInventoryByProductName(String productName);

    List<InventoryResponse> getAllInventories();

    InventoryResponse increaseInventory(UpdateInventoryRequest request);

    InventoryResponse decreaseInventory(UpdateInventoryRequest request);
}
