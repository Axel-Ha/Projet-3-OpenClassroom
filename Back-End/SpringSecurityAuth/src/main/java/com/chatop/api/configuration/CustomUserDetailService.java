package com.chatop.api.configuration;

import com.chatop.api.model.DBUser;
import com.chatop.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService
//        implements UserDetailsService
{

    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Bean
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DBUser user =  userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
//
//    }
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

}
