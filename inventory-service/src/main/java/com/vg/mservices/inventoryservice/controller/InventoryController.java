package com.vg.mservices.inventoryservice.controller;

import com.vg.mservices.inventoryservice.dto.request.CreateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.request.UpdateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.response.InventoryResponse;
import com.vg.mservices.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@Valid @RequestBody CreateInventoryRequest request){
        InventoryResponse response = inventoryService.createInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<InventoryResponse> getInventoryByProductName(@PathVariable String productName){
        InventoryResponse response = inventoryService.getInventoryByProductName(productName);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory(){
        List<InventoryResponse> response = inventoryService.getAllInventories();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/increase")
    public ResponseEntity<InventoryResponse> increaseInventory(@Valid @RequestBody UpdateInventoryRequest request){
        InventoryResponse response = inventoryService.increaseInventory(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/decrease")
    public ResponseEntity<InventoryResponse> decreaseInventory(@Valid @RequestBody UpdateInventoryRequest request){
        InventoryResponse response = inventoryService.decreaseInventory(request);
        return ResponseEntity.ok(response);
    }
}
