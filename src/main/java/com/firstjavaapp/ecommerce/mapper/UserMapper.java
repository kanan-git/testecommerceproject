package com.firstjavaapp.ecommerce.mapper;

import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;
import com.firstjavaapp.ecommerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "store", ignore = true)
    User toEntity(UserRequestDto requestDto);

    UserResponseDto toResponse(User user);

    List<UserResponseDto> toResponseList(List<User> users);

    @Mapping(target = "store", ignore = true)
    void updateEntity(UserRequestDto requestDto, @MappingTarget User user);
}
