package com.firstjavaapp.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record StoreRequestDto(
        @NotBlank
        String name,

        List<Long> productIds,

        @NotNull
        Long userId
) {
}
