package com.chatop.api.services;

//import com.chatop.api.mapper.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.DtoUser;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.model.DBUser;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository  userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public DBUser getUserById(final Long id){
        System.out.println("User Service getUser 3");
        return userRepository.findById(id).orElseThrow();
    }


    public Iterable<DBUser> getUsers(){
        System.out.println("User Service getUsers 3");
        return userRepository.findAll();
    }
}
