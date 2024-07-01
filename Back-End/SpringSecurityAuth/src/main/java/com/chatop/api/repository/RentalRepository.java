package com.chatop.api.repository;

import com.chatop.api.domain.entity.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
}
