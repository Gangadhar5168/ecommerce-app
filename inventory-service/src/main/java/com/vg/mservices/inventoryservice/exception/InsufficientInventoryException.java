package com.vg.mservices.inventoryservice.exception;

public class InsufficientInventoryException extends RuntimeException{
    public InsufficientInventoryException(String message){
        super(message);
    }
}
