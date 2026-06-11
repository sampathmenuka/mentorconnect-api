package com.example.mentorconnect_api.dto;

import jakarta.validation.constraints.*;

public record LoginRequest(

        @Email
        String email,

        @NotBlank
        String password
) {
}
