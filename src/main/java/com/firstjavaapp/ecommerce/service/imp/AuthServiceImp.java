package com.firstjavaapp.ecommerce.service.imp;

import com.firstjavaapp.ecommerce.dto.AuthResponseDto;
import com.firstjavaapp.ecommerce.dto.LoginRequestDto;
import com.firstjavaapp.ecommerce.dto.RegisterRequestDto;
import com.firstjavaapp.ecommerce.dto.UserRequestDto;
import com.firstjavaapp.ecommerce.enums.RoleEnum;
import com.firstjavaapp.ecommerce.exception.InvalidCredentialsException;
import com.firstjavaapp.ecommerce.exception.NotFoundException;
import com.firstjavaapp.ecommerce.repository.UserRepository;
import com.firstjavaapp.ecommerce.service.AuthService;
import com.firstjavaapp.ecommerce.service.JwtService;
import com.firstjavaapp.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImp(
            UserService userService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequestDto registerDto) {
        UserRequestDto newUser = new UserRequestDto(
                registerDto.firstName(),
                registerDto.lastName(),
                RoleEnum.CUSTOMER,
                registerDto.email(),
                passwordEncoder.encode(registerDto.password()),
                null
        );
        userService.createUser(newUser);
    }

    public AuthResponseDto login(LoginRequestDto loginDto) {
        var user = userRepository.findByEmail(loginDto.email());
        if(user == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.toString());
        }
        if(!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new InvalidCredentialsException(HttpStatus.BAD_REQUEST.toString());
        }
        String token = jwtService.generateToken(user);
        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
