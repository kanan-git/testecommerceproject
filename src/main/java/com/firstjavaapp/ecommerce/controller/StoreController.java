package com.firstjavaapp.ecommerce.controller;

import com.firstjavaapp.ecommerce.dto.ApiResponseDto;
import com.firstjavaapp.ecommerce.dto.StoreRequestDto;
import com.firstjavaapp.ecommerce.dto.StoreResponseDto;
import com.firstjavaapp.ecommerce.exception.AlreadyExistException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("stores")
    public ApiResponseDto<List<StoreResponseDto>> getAllStores() {
        var stores = storeService.getAllStores();
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, stores);
    }

    @GetMapping("stores/{id}")
    public ApiResponseDto<StoreResponseDto> getStoreById(@PathVariable Long id) {
        StoreResponseDto result = null;
        try {
            result = storeService.getStoreById(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, result);
    }

    @PostMapping("stores/new")
    public ApiResponseDto createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        try {
            storeService.createStore(requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(201, HttpStatus.CREATED.toString(), true);
    }

    @PutMapping("stores/{id}")
    public ApiResponseDto updateStore(@PathVariable Long id, @Valid @RequestBody StoreRequestDto requestDto) {
        try {
            storeService.updateStore(id, requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }

    @DeleteMapping("stores/{id}")
    public ApiResponseDto deleteStore(@PathVariable Long id) {
        try {
            storeService.deleteStore(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }
}
