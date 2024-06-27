package com.chatop.api.domain.entity;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name= "name")
    private String name;

    @Column(name= "password")
    private String password;

    @Column(name= "created_at")
    private Date createdAt;

    @Column(name= "updated_at")
    private Date updatedAt;

    //Ne vas pas etre mapper, defaut = USER
    @Transient
    private final String role = "USER";

}