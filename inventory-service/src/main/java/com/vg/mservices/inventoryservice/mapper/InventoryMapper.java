package com.vg.mservices.inventoryservice.mapper;

import com.vg.mservices.inventoryservice.dto.request.CreateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.response.InventoryResponse;
import com.vg.mservices.inventoryservice.entity.ProductInventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryMapper {
   public static InventoryResponse toInventoryResponse(ProductInventory productInventory){
        return InventoryResponse.builder()
                .id(productInventory.getId())
                .productName(productInventory.getProductName())
                .quantity(productInventory.getAvailableQuantity())
                .lastUpdated(productInventory.getLastUpdated())
                .build();
    }

   public static ProductInventory toProductInventory(CreateInventoryRequest request){
        return ProductInventory.builder()
                .productName(request.getProductName())
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }

    public static List<InventoryResponse> toInventoryResponseList(List<ProductInventory> productInventories){
        List<InventoryResponse> responses = new ArrayList<>();
        for(ProductInventory productInventory: productInventories){
            responses.add(toInventoryResponse(productInventory));
        }
        return responses;
    }
}
