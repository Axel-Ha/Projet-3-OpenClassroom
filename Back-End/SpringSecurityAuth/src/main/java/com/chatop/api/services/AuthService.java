package com.chatop.api.services;

import com.chatop.api.domain.dto.LoginUserDto;
import com.chatop.api.domain.dto.UserDto;
import com.chatop.api.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import com.chatop.api.domain.dto.RegisterUserDto;
import com.chatop.api.domain.entity.AuthResponse;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.mapper.RegisterMapper;
import com.chatop.api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserMapper userMapper;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       RegisterMapper registerMapper,
                       UserService userService,
                       JWTService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.registerMapper = registerMapper;
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    //Register a user
    public AuthResponse register(RegisterUserDto registerUserDto){
        log.info("AuthService : register 3");

        // Create a new User
        UserEntity userEntity = registerMapper.registerDtoToUserEntity(registerUserDto);
        userEntity.setEmail(registerUserDto.getEmail());
        userEntity.setName(registerUserDto.getName());

        // Encode password
        userEntity.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        /* Get the current DayTime */
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());

        //Save the user in DB
        userRepository.save(userEntity);

        //Load the user details using the email provided in the registration DTO
        UserDetails userDetails = userService.loadUserByUsername(registerUserDto.getEmail());

        //Generate a JWT token for the registered user using their user details
        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    //login a user
    public AuthResponse login(LoginUserDto loginUserDto){
        log.info("AuthService : login 3");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );
        //Load the user details using the email provided in the registration DTO
        UserDetails userDetails = userService.loadUserByUsername(loginUserDto.getEmail());

        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    public  UserDto authenticationUser() {
        log.info("AuthService : authenticationUser ");
        // Retrieve the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email (username) from the authentication object
        String email = authentication.getName();

        /*Use the email to find the corresponding user in the repository
         If the user is found, map the UserEntity to a UserDto using userMapper
         If no user is found, throw an exception */
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserDto)
                .orElseThrow();
    }

}
