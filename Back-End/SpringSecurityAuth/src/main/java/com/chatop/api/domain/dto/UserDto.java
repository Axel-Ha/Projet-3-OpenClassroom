package com.chatop.api.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private String password;

    private Date created_at;

    private Date updated_at;

}
