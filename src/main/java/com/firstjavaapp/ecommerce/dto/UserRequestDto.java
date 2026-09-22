package com.firstjavaapp.ecommerce.dto;

import com.firstjavaapp.ecommerce.enums.RoleEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        String firstName,

        String lastName,

        @NotNull
        RoleEnum role,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, max = 32)
        String password,

        Long storeId
) {
}
