package com.firstjavaapp.ecommerce.controller;

import com.firstjavaapp.ecommerce.dto.ApiResponseDto;
import com.firstjavaapp.ecommerce.dto.ProductRequestDto;
import com.firstjavaapp.ecommerce.dto.ProductResponseDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;
import com.firstjavaapp.ecommerce.exception.AlreadyExistException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ApiResponseDto<List<ProductResponseDto>> getAllProducts() {
        var products = productService.getAllProducts();
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, products);
    }

    @GetMapping("products/{id}")
    public ApiResponseDto<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto result = null;
        try {
            result = productService.getProductById(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(200, HttpStatus.OK.toString(), true, result);
    }

    @PostMapping("products/new")
    public ApiResponseDto createProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        try {
            productService.createProduct(requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(201, HttpStatus.CREATED.toString(), true);
    }

    @PutMapping("products/{id}")
    public ApiResponseDto updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto) {
        try {
            productService.updateProduct(id, requestDto);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        } catch(AlreadyExistException exception) {
            return new ApiResponseDto(409, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }

    @DeleteMapping("products/{id}")
    public ApiResponseDto deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
        } catch(NotFoundException exception) {
            return new ApiResponseDto(404, exception.getMessage(), false);
        }
        return new ApiResponseDto(204, HttpStatus.NO_CONTENT.toString(), true);
    }
}
