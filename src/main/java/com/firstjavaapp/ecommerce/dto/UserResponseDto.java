package com.firstjavaapp.ecommerce.dto;

import com.firstjavaapp.ecommerce.enums.RoleEnum;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        RoleEnum role,
        String email,
        StoreResponseDto store
) {
}
