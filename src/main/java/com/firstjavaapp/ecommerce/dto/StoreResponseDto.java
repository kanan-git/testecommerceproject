package com.firstjavaapp.ecommerce.dto;

import java.util.List;

public record StoreResponseDto(
        Long id,
        String name,
        List<ProductResponseDto> products,
        UserResponseDto user
) {
}
