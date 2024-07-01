package com.chatop.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "name")
    private String name;

    @Column(name= "surface")
    private Integer surface;

    @Column(name= "price")
    private Integer price;

    @Column(name= "picture")
    private MultipartFile picture;

    @Column(name= "description")
    private String description;

    @Id
    @Column(name= "owner_id")
    private Long ownerId;

    @Column(name= "created_at")
    private Date createdAt;

    @Column(name= "updated_at")
    private Date updatedAt;
}
