package com.chatop.api.mapper;

import com.chatop.api.domain.dto.User;
import com.chatop.api.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
        public User userEntityToUserDto(UserEntity user) {
        if (user == null) {
            return null;
        } else {
            User dtoUser = new User();
            dtoUser.setId(user.getId());
            dtoUser.setEmail(user.getEmail());
            dtoUser.setName(user.getName());
            dtoUser.setCreated_at(user.getCreatedAt());
            dtoUser.setUpdated_at(user.getUpdatedAt());
            return dtoUser;
        }
    }

    public static UserEntity toEntity(User dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
