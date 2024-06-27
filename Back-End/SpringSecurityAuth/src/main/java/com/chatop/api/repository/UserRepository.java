package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.domain.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
