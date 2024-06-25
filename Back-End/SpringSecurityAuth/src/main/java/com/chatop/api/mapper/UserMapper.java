package com.chatop.api.mapper;

import com.chatop.api.dto.DtoUser;
import com.chatop.api.model.DBUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
        public DtoUser userEntityToUserDto(DBUser user) {
        if (user == null) {
            return null;
        } else {
            DtoUser dtoUser = new DtoUser();
            dtoUser.setId(user.getId());
            dtoUser.setEmail(user.getEmail());
            dtoUser.setName(user.getName());
            dtoUser.setCreated_at(user.getCreated_at());
            dtoUser.setUpdated_at(user.getUpdated_at());
            return dtoUser;
        }
    }

    public static DBUser toEntity(DtoUser dto) {
        DBUser user = new DBUser();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
