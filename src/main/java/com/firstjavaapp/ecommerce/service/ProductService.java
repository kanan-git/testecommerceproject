package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.ProductRequestDto;
import com.firstjavaapp.ecommerce.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    public List<ProductResponseDto> getAllProducts();
    public ProductResponseDto getProductById(Long id);
    public void createProduct(ProductRequestDto requestDto);
    public void updateProduct(Long id, ProductRequestDto requestDto);
    public void deleteProduct(Long id);
}
