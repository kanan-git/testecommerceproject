package com.firstjavaapp.ecommerce.controller;

import com.firstjavaapp.ecommerce.dto.ApiResponseDto;
import com.firstjavaapp.ecommerce.dto.AuthResponseDto;
import com.firstjavaapp.ecommerce.dto.LoginRequestDto;
import com.firstjavaapp.ecommerce.dto.RegisterRequestDto;
import com.firstjavaapp.ecommerce.enums.RoleEnum;
import com.firstjavaapp.ecommerce.service.AuthService;
import com.firstjavaapp.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("auth/register")
    public ApiResponseDto register(@Valid @RequestBody RegisterRequestDto registerDto) {
        return new ApiResponseDto(200, "", true);
    }

    @PostMapping("auth/login")
    public ApiResponseDto<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginDto) {
        var claims = new AuthResponseDto("", new Long(0), "", RoleEnum.CUSTOMER);
        return new ApiResponseDto(200, "", true, claims);
    }
}
