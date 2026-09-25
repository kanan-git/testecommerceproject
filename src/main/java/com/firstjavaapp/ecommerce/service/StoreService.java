package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.StoreRequestDto;
import com.firstjavaapp.ecommerce.dto.StoreResponseDto;

import java.util.List;

public interface StoreService {
    public List<StoreResponseDto> getAllStores();
    public StoreResponseDto getStoreById(Long id);
    public void createStore(StoreRequestDto requestDto);
    public void updateStore(Long id, StoreRequestDto requestDto);
    public void deleteStore(Long id);
}
