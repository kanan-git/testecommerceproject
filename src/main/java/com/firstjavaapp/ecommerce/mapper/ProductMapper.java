package com.firstjavaapp.ecommerce.mapper;

import com.firstjavaapp.ecommerce.dto.ProductRequestDto;
import com.firstjavaapp.ecommerce.dto.ProductResponseDto;
import com.firstjavaapp.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "store", ignore = true)
    Product toEntity(ProductRequestDto requestDto);

    ProductResponseDto toResponse(Product product);

    List<ProductResponseDto> toResponseList(List<Product> products);

    @Mapping(target = "store", ignore = true)
    void updateEntity(ProductRequestDto requestDto, @MappingTarget Product product);
}
