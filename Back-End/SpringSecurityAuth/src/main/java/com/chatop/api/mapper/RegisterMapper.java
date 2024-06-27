package com.chatop.api.mapper;

import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.domain.dto.RegisterUserDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {

    public UserEntity registerDtoToUserEntity(RegisterUserDto registerUserDto) {
        if (registerUserDto == null) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerUserDto.getEmail());
        user.setName(registerUserDto.getName());
        user.setPassword(registerUserDto.getPassword());

        return user;
    }
}
