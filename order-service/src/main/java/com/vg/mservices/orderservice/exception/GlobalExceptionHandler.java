package com.vg.mservices.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        for(FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation Failed")
                .errors(errors)
                .build();

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message("Order Not found")
                .errors(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(OrderAlreadyCancelledException.class)
    public ResponseEntity<ErrorResponse> handleOrderAlreadyCancelledException(OrderAlreadyCancelledException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message("Order already cancelled")
                .errors(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
}
