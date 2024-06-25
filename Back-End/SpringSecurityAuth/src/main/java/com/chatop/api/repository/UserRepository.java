package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.DBUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<DBUser, Long> {

}