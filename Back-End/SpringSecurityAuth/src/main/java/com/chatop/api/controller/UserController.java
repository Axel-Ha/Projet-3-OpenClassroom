package com.chatop.api.controller;
import com.chatop.api.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.services.UserService;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        log.info("Controller getUser() 2");
        return ResponseEntity.ok(userService.getUserById(id));
    }
}


