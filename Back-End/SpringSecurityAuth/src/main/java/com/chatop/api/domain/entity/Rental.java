package com.chatop.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private String picture;

    @Column(name= "description")
    private String description;

    @Column(name= "owner_id")
    private Long userId;

    @Column(name= "created_at")
    private Date createdAt;

    @Column(name= "updated_at")
    private Date updatedAt;
}
