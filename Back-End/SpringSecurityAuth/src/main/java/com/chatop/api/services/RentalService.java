package com.chatop.api.services;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.entity.Rental;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.mapper.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.rentalMapper = rentalMapper;
    }


    public void saveRental(RentalDto rentalDto){
        // Retrieve the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Extract the email (username) from the authentication object
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email).orElseThrow();
        Rental rental = rentalMapper.toRental(rentalDto);
        rental.setUserId(user.getId());

        rentalRepository.save(rental);
    }
}
