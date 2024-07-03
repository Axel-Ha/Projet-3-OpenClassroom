package com.chatop.api.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class RentalDto {

    private Long id;

    private String name;

    private Integer surface;

    private Integer price;

    private String picture;

    private String description;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;
}
