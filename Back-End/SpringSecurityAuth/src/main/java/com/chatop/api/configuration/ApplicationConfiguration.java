package com.chatop.api.configuration;

import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    // Define a UserDetailsService bean to load user-specific data during authentication
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .map(this::convertToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    // Define a BCryptPasswordEncoder bean to encrypt passwords
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // Define an AuthenticationManager bean to manage authentication processes
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    // Define an AuthenticationProvider bean for DaoAuthenticationProvider configuration
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Convert UserEntity to Spring Security UserDetails
    private UserDetails convertToUserDetails(UserEntity userEntity) {
        return User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList()) // Can add user roles here if needed
                .build();
    }
}