package com.firstjavaapp.ecommerce.controller;

import com.firstjavaapp.ecommerce.dto.ApiResponseDto;
import com.firstjavaapp.ecommerce.dto.AuthResponseDto;
import com.firstjavaapp.ecommerce.dto.LoginRequestDto;
import com.firstjavaapp.ecommerce.dto.RegisterRequestDto;
import com.firstjavaapp.ecommerce.enums.RoleEnum;
import com.firstjavaapp.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("auth/register")
    public ApiResponseDto register(@Valid @RequestBody RegisterRequestDto registerDto) {
        authService.register(registerDto);
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true);
    }

    @PostMapping("auth/login")
    public ApiResponseDto<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginDto) {
        var result = authService.login(loginDto);
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, result);
    }
}
