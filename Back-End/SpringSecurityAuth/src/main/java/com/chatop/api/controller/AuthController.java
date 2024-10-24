package com.chatop.api.controller;

import com.chatop.api.domain.dto.LoginUserDto;
import com.chatop.api.domain.dto.RegisterUserDto;
import com.chatop.api.domain.dto.UserDto;
import com.chatop.api.domain.entity.AuthResponse;
import com.chatop.api.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.chatop.api.configuration.SwaggerConfig.NAME_SECURITY_REQUIREMENT;


@Slf4j
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register a user",
            description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserDto registerUserDto){
        return ResponseEntity.ok(authService.register(registerUserDto));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Log in a user",
            description = "Log in a user with their credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserDto loginUserDto){
        return ResponseEntity.ok(authService.login(loginUserDto));
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get authenticated user",
            description = "Retrieve the authenticated user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user details"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated")
    })
    @SecurityRequirement(name=NAME_SECURITY_REQUIREMENT)
    public ResponseEntity<UserDto> authenticatedUser(){
        return ResponseEntity.ok(authService.authenticationUser());
    }



}
