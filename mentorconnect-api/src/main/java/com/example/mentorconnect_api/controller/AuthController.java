package com.example.mentorconnect_api.controller;

import com.example.mentorconnect_api.dto.*;
import com.example.mentorconnect_api.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody
            @Valid
            RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody
            @Valid
            LoginRequest request) {

        return authService.login(request);
    }
}
