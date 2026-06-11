package com.example.mentorconnect_api.seeder;

import com.example.mentorconnect_api.entity.*;
import com.example.mentorconnect_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder
        implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String email = "admin@gmail.com";

        if(userRepository.existsByEmail(email)) {
            return;
        }

        User admin = User.builder()
                .name("System Admin")
                .email(email)
                .password(
                        passwordEncoder.encode(
                                "Admin@123"))
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(admin);

        System.out.println(
                "Admin account created");
    }
}
