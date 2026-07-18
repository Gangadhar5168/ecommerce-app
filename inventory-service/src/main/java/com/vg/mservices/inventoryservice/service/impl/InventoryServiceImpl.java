package com.vg.mservices.inventoryservice.service.impl;

import com.vg.mservices.inventoryservice.dto.request.CreateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.request.UpdateInventoryRequest;
import com.vg.mservices.inventoryservice.dto.response.InventoryResponse;
import com.vg.mservices.inventoryservice.entity.ProductInventory;
import com.vg.mservices.inventoryservice.mapper.InventoryMapper;
import com.vg.mservices.inventoryservice.repository.ProductInventoryRepository;
import com.vg.mservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ProductInventoryRepository productInventoryRepository;

    @Override
    public InventoryResponse createInventory(CreateInventoryRequest request) {
        ProductInventory product = InventoryMapper.toProductInventory(request);
        product.setLastUpdated(LocalDateTime.now());
        ProductInventory savedProduct = productInventoryRepository.save(product);
        return InventoryMapper.toInventoryResponse(savedProduct);
    }

        @Override
        public InventoryResponse getInventoryByProductName(String productName){
            ProductInventory product = productInventoryRepository.findByProductName(productName).orElseThrow();
            return InventoryMapper.toInventoryResponse(product);
        }

        @Override
        public List<InventoryResponse> getAllInventories() {
        List<ProductInventory> products = productInventoryRepository.findAll();
            return InventoryMapper.toInventoryResponseList(products);
        }

        @Override
        public InventoryResponse increaseInventory(UpdateInventoryRequest request){
        ProductInventory product = productInventoryRepository.findByProductName(request.getProductName()).orElseThrow();
        product.setAvailableQuantity(product.getAvailableQuantity()+ request.getQuantity());
        product.setLastUpdated(LocalDateTime.now());
        ProductInventory updatedProduct = productInventoryRepository.save(product);
            return InventoryMapper.toInventoryResponse(updatedProduct);
        }

        @Override
        public InventoryResponse decreaseInventory (UpdateInventoryRequest request){
        ProductInventory product = productInventoryRepository.findByProductName(request.getProductName()).orElseThrow();
        if(product.getAvailableQuantity()==0 ){
            throw new RuntimeException("Out of stock");
        }
        if(request.getQuantity()>product.getAvailableQuantity()){
            throw new RuntimeException("Only "+product.getAvailableQuantity()+" products are in stock");
        }
        product.setAvailableQuantity(product.getAvailableQuantity()- request.getQuantity());
        product.setLastUpdated(LocalDateTime.now());
        ProductInventory updatedProduct = productInventoryRepository.save(product);
        return InventoryMapper.toInventoryResponse(updatedProduct);
    }
}