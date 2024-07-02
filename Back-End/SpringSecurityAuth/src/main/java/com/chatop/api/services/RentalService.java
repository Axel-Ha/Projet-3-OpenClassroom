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

import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;
    private final PictureService pictureService;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, RentalMapper rentalMapper, PictureService pictureService) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.rentalMapper = rentalMapper;
        this.pictureService = pictureService;
    }


    public void saveRental(RentalDto rentalDto) throws IOException {

        // Retrieve the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email (username) from the authentication object
        String email = authentication.getName();

        UserEntity user = userRepository.findByEmail(email).orElseThrow();
        Rental rental = rentalMapper.toRental(rentalDto);

        String picDbPath = pictureService.saveImage(rentalDto.getPicture());
        rental.setPicture(picDbPath);

        rental.setUserId(user.getId());
        rental.setCreatedAt(new Date());
        rental.setUpdatedAt(new Date());
        log.info("RentalService : saveRental()");
        rentalRepository.save(rental);
    }
}
