package com.chatop.api.services;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.dto.RentalsDto;
import com.chatop.api.domain.entity.Rental;
import com.chatop.api.domain.entity.UserEntity;
import com.chatop.api.mapper.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RentalService {

    private final String pictureApiPath = "/api/pictures/" ;
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

    @Transactional(readOnly = true)
    public RentalDto getRental(@PathVariable final Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        RentalDto rentalDto = rentalMapper.rentalToRentalDto(rental);
        rentalDto.setPicture(pictureApiPath + rental.getPicture());
        return rentalDto;

    }

    @Transactional(readOnly = true)
    public RentalsDto getAllRentals() {
        //Will not be changed
        final List<RentalDto> allRentals = rentalRepository.findAll().stream()
                .map(rental -> {
                    RentalDto rentalDto = rentalMapper.rentalToRentalDto(rental);

                    // Attempt to retrieve the picture
                    try {
                        rentalDto.setPicture(pictureApiPath + rental.getPicture());
                    } catch (Exception e) {
                        throw new RuntimeException("Error serving file", e);
                    }

                    return rentalDto;
                })
                .toList();

        RentalsDto rentals = new RentalsDto();
        rentals.setRentals(allRentals);

        return rentals;
    }



    public void saveRental(String name,
                            int surface,
                            int price,
                            MultipartFile picture,
                            String description) throws IOException {

        // Retrieve the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email (username) from the authentication object
        String email = authentication.getName();

        UserEntity user = userRepository.findByEmail(email).orElseThrow();
        try{
            String picPath = pictureService.savePicture(picture);
            Rental rental = new Rental();
            rental.setDescription(description);
            rental.setName(name);
            rental.setPrice(price);
            rental.setSurface(surface);
            rental.setPicture(picPath);
            rental.setUserId(user.getId());
            rental.setCreatedAt(new Date());
            rental.setUpdatedAt(new Date());
            rentalRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void updateRental(Long id, String name, int surface, int price, String description) {
        Rental rentalToUpdate = rentalRepository.findById(id).orElseThrow();
        rentalToUpdate.setName(name);
        rentalToUpdate.setSurface(surface);
        rentalToUpdate.setPrice(price);
        rentalToUpdate.setDescription(description);
        rentalToUpdate.setUpdatedAt(new Date());
        rentalRepository.save(rentalToUpdate);
    }
}
