package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.dto.UserResponseDto;
import com.firstjavaapp.ecommerce.entity.Store;
import com.firstjavaapp.ecommerce.entity.User;
import com.firstjavaapp.ecommerce.exception.AlreadyExistException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.mapper.UserMapper;
import com.firstjavaapp.ecommerce.repository.StoreRepository;
import com.firstjavaapp.ecommerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final UserMapper userMapper;

    public UserService(
            UserRepository userRepository,
            StoreRepository storeRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> result = userMapper.toResponseList(users);
        return result;
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        UserResponseDto result = userMapper.toResponse(user);
        return result;
    }

    public void createUser(UserRequestDto requestDto) {
        if(userRepository.existsByEmail(requestDto.email())) {
            throw new AlreadyExistException(HttpStatus.CONFLICT.toString());
        }
        User newUser = userMapper.toEntity(requestDto);
        Store store = null;
        if(requestDto.storeId() != null) {
            store = storeRepository.findById(requestDto.storeId()).orElseThrow(
                    () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
            );
        }
        if(store != null) {
            newUser.setStore(store);
        }
        userRepository.save(newUser);
    }

    public void updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        if(requestDto.email() != user.getEmail() && userRepository.existsByEmail(requestDto.email())) {
            throw new AlreadyExistException(HttpStatus.CONFLICT.toString());
        }
        userMapper.updateEntity(requestDto, user);
        Store store = null;
        if(requestDto.storeId() != null) {
            store = storeRepository.findById(requestDto.storeId()).orElseThrow(
                    () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
            );
        }
        if(store != null) {
            user.setStore(store);
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(HttpStatus.NOT_FOUND.toString())
        );
        userRepository.delete(user);
    }
}
