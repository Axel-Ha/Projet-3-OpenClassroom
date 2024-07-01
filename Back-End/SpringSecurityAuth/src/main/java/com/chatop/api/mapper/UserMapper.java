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
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setCreated_at(user.getCreatedAt());
            userDto.setUpdated_at(user.getUpdatedAt());
            return userDto;
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
