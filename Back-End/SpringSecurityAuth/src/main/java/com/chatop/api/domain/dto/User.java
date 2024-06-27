package com.chatop.api.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String email;

    private String name;

    private Date created_at;

    private Date updated_at;

}
