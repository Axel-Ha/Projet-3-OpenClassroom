package com.chatop.api.controller;

import com.chatop.api.domain.dto.LoginUserDto;
import com.chatop.api.domain.dto.RegisterUserDto;
import com.chatop.api.domain.dto.UserDto;
import com.chatop.api.domain.entity.AuthResponse;
import com.chatop.api.services.AuthService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        log.info("AuthController : route register  2");
        return ResponseEntity.ok(authService.register(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserDto loginUserDto){
        log.info("AuthController : route login    2");
        return ResponseEntity.ok(authService.login(loginUserDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> authenticatedUser(){
        log.info("AuthController : route authenticatedUser  2");
        return ResponseEntity.ok(authService.authenticationUser());
    }


}
