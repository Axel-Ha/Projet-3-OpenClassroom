package com.chatop.api.services;

import lombok.extern.slf4j.Slf4j;
import com.chatop.api.domain.dto.RegisterUserDto;
import com.chatop.api.domain.entity.AuthResponse;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.mapper.RegisterMapper;
import com.chatop.api.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RegisterMapper registerMapper;
    private final UserService userService;
    private final JWTService jwtService;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       RegisterMapper registerMapper,
                       UserService userService,
                       JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.registerMapper = registerMapper;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /* Register a user */
    public AuthResponse register(RegisterUserDto registerUserDto){
        log.info("AuthService : register ");

        /* Create a new User*/
        UserEntity userEntity = registerMapper.registerDtoToUserEntity(registerUserDto);
        userEntity.setEmail(registerUserDto.getEmail());
        userEntity.setName(registerUserDto.getName());

        /* Encode password */
        userEntity.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        /* Get the current DayTime */
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());

        /* Save the user in DB */
        userRepository.save(userEntity);

        /* Load the user details using the email provided in the registration DTO  */
        UserDetails userDetails = userService.loadUserByUsername(registerUserDto.getEmail());

        /*Generate a JWT token for the registered user using their user details*/
        return new AuthResponse(jwtService.generateToken(userDetails));


    }
}
