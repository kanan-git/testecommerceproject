package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto getUserById(Long id);
    public void createUser(UserRequestDto requestDto);
    public void updateUser(Long id, UserRequestDto requestDto);
    public void deleteUser(Long id);
}
