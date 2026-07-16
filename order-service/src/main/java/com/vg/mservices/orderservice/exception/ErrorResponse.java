package com.vg.mservices.orderservice.exception;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private Integer status;
    private String message;
    private Object errors;
}
