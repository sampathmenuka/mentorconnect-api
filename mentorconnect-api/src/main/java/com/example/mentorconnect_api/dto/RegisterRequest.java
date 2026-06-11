package com.example.mentorconnect_api.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(

        @NotBlank
        String name,

        @Email
        String email,

        @Size(min = 6)
        String password
) {
}
