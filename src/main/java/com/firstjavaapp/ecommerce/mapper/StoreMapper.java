package com.firstjavaapp.ecommerce.mapper;

import com.firstjavaapp.ecommerce.dto.StoreRequestDto;
import com.firstjavaapp.ecommerce.dto.StoreResponseDto;
import com.firstjavaapp.ecommerce.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "user", ignore = true)
    Store toEntity(StoreRequestDto requestDto);

    StoreResponseDto toResponse(Store store);

    List<StoreResponseDto> toResponseList(List<Store> stores);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(StoreRequestDto requestDto, @MappingTarget Store store);
}
