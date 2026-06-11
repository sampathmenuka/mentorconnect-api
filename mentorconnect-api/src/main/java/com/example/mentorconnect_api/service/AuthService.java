package com.example.mentorconnect_api.service;

import com.example.mentorconnect_api.dto.*;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
