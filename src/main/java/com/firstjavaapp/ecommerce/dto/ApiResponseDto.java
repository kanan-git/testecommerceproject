package com.firstjavaapp.ecommerce.dto;

public record ApiResponseDto<S>(
        int status,
        String message,
        boolean success,
        S data
) {
    public ApiResponseDto(
            Integer status,
            String message,
            Boolean success
    ) {
        this(status, message, success, null);
    }
}
