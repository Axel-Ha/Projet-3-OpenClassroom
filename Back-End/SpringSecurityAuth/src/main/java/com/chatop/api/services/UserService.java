package com.chatop.api.services;

import com.chatop.api.mapper.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.DtoUser;
import com.chatop.api.repository.UserRepository;

import org.springframework.web.bind.annotation.PathVariable;


@Data
@Service
public class UserService {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public DtoUser getUserById(@PathVariable final Long id){
        System.out.println("UserService getUserById 3");
        return userRepository.findById(id).map(userMapper::userEntityToUserDto).orElseThrow();
    }
}
