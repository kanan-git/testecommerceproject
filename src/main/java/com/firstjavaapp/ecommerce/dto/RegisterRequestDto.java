package com.firstjavaapp.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank
        @Size(max=50)
        String firstName,

        @Size(max=50)
        String lastName,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min=8, max=32)
        String password
) {
}
