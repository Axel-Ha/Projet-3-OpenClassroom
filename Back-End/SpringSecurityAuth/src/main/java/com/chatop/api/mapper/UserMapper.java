package com.chatop.api.mapper;

import com.chatop.api.domain.dto.UserDto;
import com.chatop.api.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
        public UserDto userEntityToUserDto(UserEntity user) {
        if (user == null) {
            return null;
        } else {
            UserDto dtoUserDto = new UserDto();
            dtoUserDto.setId(user.getId());
            dtoUserDto.setEmail(user.getEmail());
            dtoUserDto.setName(user.getName());
            dtoUserDto.setCreated_at(user.getCreatedAt());
            dtoUserDto.setUpdated_at(user.getUpdatedAt());
            return dtoUserDto;
        }
    }

    public static UserEntity toEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
