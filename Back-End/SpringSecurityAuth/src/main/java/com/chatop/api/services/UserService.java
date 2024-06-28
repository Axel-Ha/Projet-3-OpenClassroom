package com.chatop.api.services;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.api.repository.UserRepository;
import com.chatop.api.domain.dto.UserDto;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.mapper.UserMapper;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;


@Slf4j
@Data
@Service
public class UserService implements UserDetailsService {

    private final UserRepository  userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto getUserById(@PathVariable final Long id){
        System.out.println("UserService getUserById 3");
        return userRepository.findById(id).map(userMapper::userEntityToUserDto).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("UserService : loadUserByUsername  4");
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.singletonList(authority));

    }
}
