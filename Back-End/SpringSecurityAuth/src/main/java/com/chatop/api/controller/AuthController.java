package com.chatop.api.controller;

import com.chatop.api.domain.dto.RegisterUserDto;
import com.chatop.api.domain.entity.AuthResponse;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.services.AuthService;
import com.chatop.api.services.JWTService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserDto registerUserDto){
        log.info("AuthController : route register");
        return ResponseEntity.ok(authService.register(registerUserDto));
    }

}
