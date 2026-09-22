package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.StoreRequestDto;
import com.firstjavaapp.ecommerce.dto.StoreResponseDto;
import com.firstjavaapp.ecommerce.entity.Product;
import com.firstjavaapp.ecommerce.entity.Store;
import com.firstjavaapp.ecommerce.entity.User;
import com.firstjavaapp.ecommerce.exception.AlreadyExistException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.mapper.StoreMapper;
import com.firstjavaapp.ecommerce.repository.ProductRepository;
import com.firstjavaapp.ecommerce.repository.StoreRepository;
import com.firstjavaapp.ecommerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StoreMapper storeMapper;

    public StoreService(
            StoreRepository storeRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            StoreMapper storeMapper
    ) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.storeMapper = storeMapper;
    }

    public List<StoreResponseDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreResponseDto> result = storeMapper.toResponseList(stores);
        return result;
    }

    public StoreResponseDto getStoreById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        StoreResponseDto result = storeMapper.toResponse(store);
        return result;
    }

    public void createStore(StoreRequestDto requestDto) {
        if(storeRepository.existsByName(requestDto.name())) {
            throw new AlreadyExistException(HttpStatus.CONFLICT.toString());
        }
        Store newStore = storeMapper.toEntity(requestDto);
        User user = null;
        List<Product> products = new ArrayList<>();
        if(requestDto.productIds() != null) {
            products = productRepository.findAllById(requestDto.productIds());
        }
        if(requestDto.userId() != null) {
            user = userRepository.findById(requestDto.userId()).orElseThrow(
                    () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
            );
        }
        if(products != null) {
            newStore.setProducts(products);
        }
        if(user != null) {
            newStore.setUser(user);
        }
        storeRepository.save(newStore);
    }

    public void updateStore(Long id, StoreRequestDto requestDto) {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        if(requestDto.name() != store.getName() && storeRepository.existsByName(requestDto.name())) {
            throw new AlreadyExistException(HttpStatus.CONFLICT.toString());
        }
        storeMapper.updateEntity(requestDto, store);
        List<Product> products = productRepository.findAllById(requestDto.productIds());
        User user = userRepository.findById(requestDto.userId()).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        store.setProducts(products);
        store.setUser(user);
        storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        storeRepository.delete(store);
    }
}
