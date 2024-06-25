package com.chatop.api.dto;

import com.chatop.api.model.DBUser;
import lombok.Data;

import java.util.Date;

@Data
public class DtoUser {

    private Long id;

    private String email;

    private String name;

    private Date created_at;

    private Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

//    public DtoUser userEntityToUserDto(DBUser user) {
//        if (user == null) {
//            return null;
//        } else {
//            DtoUser dtoUser = new DtoUser();
//            dtoUser.setId(user.getId());
//            dtoUser.setEmail(user.getEmail());
//            dtoUser.setName(user.getName());
//            dtoUser.setCreated_at(user.getCreated_at());
//            dtoUser.setUpdated_at(user.getUpdated_at());
//            return dtoUser;
//        }
//    }
}
