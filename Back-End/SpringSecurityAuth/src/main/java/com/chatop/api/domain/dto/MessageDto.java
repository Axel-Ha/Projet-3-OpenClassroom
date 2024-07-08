package com.chatop.api.domain.dto;

import lombok.Data;

import java.util.Date;
@Data
public class MessageDto {

    private Long id;

    private Long rental_id;

    private Long user_id;

    private String message;

    private Date createdAt;

    private Date updatedAt;

}
