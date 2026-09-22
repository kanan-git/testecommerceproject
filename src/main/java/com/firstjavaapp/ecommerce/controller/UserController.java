package com.firstjavaapp.ecommerce.controller;

import com.firstjavaapp.ecommerce.dto.ApiResponseDto;
import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;
import com.firstjavaapp.ecommerce.exception.AlreadyExistException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/all")
    public ApiResponseDto<List<UserResponseDto>> getAllUsers() {
        var users = userService.getAllUsers();
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, users);
    }

    @GetMapping("user/all/{id}")
    public ApiResponseDto<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto result = null;
        try {
            result = userService.getUserById(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, result);
    }

    @PostMapping("user/new")
    public ApiResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        try {
            userService.createUser(requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(201, HttpStatus.CREATED.toString(), true);
    }

    @PutMapping("user/edit/{id}")
    public ApiResponseDto updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) {
        try {
            userService.updateUser(id, requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }

    @DeleteMapping("user/delete/{id}")
    public ApiResponseDto deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }
}
