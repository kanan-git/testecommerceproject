package com.firstjavaapp.ecommerce.dto;

public record RegisterRequestDto(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
