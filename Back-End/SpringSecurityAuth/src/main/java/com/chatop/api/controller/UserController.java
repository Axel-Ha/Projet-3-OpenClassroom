package com.chatop.api.controller;
import com.chatop.api.dto.DtoUser;
import com.chatop.api.model.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<DBUser> getUser(@PathVariable final Long id){
        System.out.println("Controller getUser() 2");
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<DBUser>> getUsers(){
        System.out.println("Controller getUsers() 2");
        return ResponseEntity.ok(userService.getUsers());
    }
}


