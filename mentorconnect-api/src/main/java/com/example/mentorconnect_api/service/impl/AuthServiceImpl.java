package com.example.mentorconnect_api.service.impl;

import com.example.mentorconnect_api.config.JwtService;
import com.example.mentorconnect_api.dto.*;
import com.example.mentorconnect_api.entity.*;
import com.example.mentorconnect_api.exception.InvalidCredentialsException;
import com.example.mentorconnect_api.exception.UserAlreadyExistsException;
import com.example.mentorconnect_api.exception.UserNotFoundException;
import com.example.mentorconnect_api.repository.UserRepository;
import com.example.mentorconnect_api.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(
            RegisterRequest request) {

        if(userRepository.existsByEmail(
                request.email())) {

            throw new UserAlreadyExistsException(
                    "Email already exists");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(
                        passwordEncoder.encode(
                                request.password()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getEmail()
        );
    }

    @Override
    public AuthResponse login(
            LoginRequest request) {

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        boolean match =
                passwordEncoder.matches(
                        request.password(),
                        user.getPassword());

        if(!match) {
            throw new InvalidCredentialsException(
                    "Invalid credentials");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getEmail()
        );
    }
}
