package com.example.mentorconnect_api.dto;

public record AuthResponse(
        String token,
        String role,
        String email
) {
}
