package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.AuthResponseDto;
import com.firstjavaapp.ecommerce.dto.LoginRequestDto;
import com.firstjavaapp.ecommerce.dto.RegisterRequestDto;

public interface AuthService {
    public void register(RegisterRequestDto registerDto);
    public AuthResponseDto login(LoginRequestDto loginDto);
}
