package com.chatop.api.domain.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RentalDto {

    private Long id;

    private String name;

    private Integer surface;

    private Integer price;

    private String picture;

    private String description;

    private Long owner_id;

    private Date created_at;

    private Date updated_at;
}
