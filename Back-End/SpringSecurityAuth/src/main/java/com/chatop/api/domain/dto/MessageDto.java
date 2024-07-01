package com.chatop.api.domain.dto;

import lombok.Data;

import java.util.Date;
@Data
public class MessageDto {

    private Long id;

    private int rentalId;

    private int userId;

    private String message;

    private Date createdAt;

    private Date updatedAt;

}
