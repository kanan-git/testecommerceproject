package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.ProductRequestDto;
import com.firstjavaapp.ecommerce.dto.ProductResponseDto;
import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;
import com.firstjavaapp.ecommerce.entity.Product;
import com.firstjavaapp.ecommerce.entity.Product;
import com.firstjavaapp.ecommerce.entity.Store;
import com.firstjavaapp.ecommerce.entity.User;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.mapper.ProductMapper;
import com.firstjavaapp.ecommerce.mapper.ProductMapper;
import com.firstjavaapp.ecommerce.mapper.UserMapper;
import com.firstjavaapp.ecommerce.repository.ProductRepository;
import com.firstjavaapp.ecommerce.repository.ProductRepository;
import com.firstjavaapp.ecommerce.repository.StoreRepository;
import com.firstjavaapp.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            StoreRepository storeRepository,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> result = productMapper.toResponseList(products);
        return result;
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        ProductResponseDto result = productMapper.toResponse(product);
        return result;
    }

    public void createProduct(ProductRequestDto requestDto) {
        Product newProduct = productMapper.toEntity(requestDto);
        Store store = storeRepository.findById(requestDto.storeId()).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        newProduct.setStore(store);
        productRepository.save(newProduct);
    }

    public void updateProduct(Long id, ProductRequestDto requestDto) {
        Store store = storeRepository.findById(requestDto.storeId()).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        productMapper.updateEntity(requestDto, product);
        product.setStore(store);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        productRepository.delete(product);
    }
}
