package com.chatop.api.services;

import com.chatop.api.domain.entity.Rental;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.exceptions.RentalNotFoundException;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Data
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public Rental getRentalById(Long id) {
        return rentalRepository
                .findById(id)
                .orElseThrow(RentalNotFoundException::new);
    }


    public void saveRental(Rental rental){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        UserEntity user = userRepository.findByEmail(currentUserEmail).orElseThrow(RentalNotFoundException::new);

        rental.setOwnerId(user.getId());
        rentalRepository.save(rental);


    }
}
