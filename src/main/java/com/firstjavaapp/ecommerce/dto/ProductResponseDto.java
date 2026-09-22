package com.firstjavaapp.ecommerce.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        String title,
        BigDecimal price,
        StoreResponseDto store
) {
}
