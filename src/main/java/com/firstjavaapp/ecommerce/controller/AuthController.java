package com.firstjavaapp.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    public AuthController() {
    }

    @PostMapping("auth/register")
    public void register() {
    }

    @PostMapping("auth/login")
    public void login() {
    }
}
