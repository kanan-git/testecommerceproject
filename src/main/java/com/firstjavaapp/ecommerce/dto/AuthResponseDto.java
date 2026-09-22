package com.firstjavaapp.ecommerce.dto;

import com.firstjavaapp.ecommerce.enums.RoleEnum;

public record AuthResponseDto(
        String token,
        Long userId,
        String email,
        RoleEnum role
) {
}
