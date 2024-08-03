package com.chatop.api.controller;
import com.chatop.api.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.services.UserService;

import static com.chatop.api.configuration.SwaggerConfig.NAME_SECURITY_REQUIREMENT;

@Slf4j
@RestController
@RequestMapping("/api")
@SecurityRequirement(name=NAME_SECURITY_REQUIREMENT)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    @Operation(
            summary = "Retrieve a user by ID",
            description = "Fetch details of a user based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}


