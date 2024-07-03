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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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


    public RentalDto getRental(@PathVariable final Long id) {
        return rentalRepository.findById(id).map(rentalMapper::rentalToRentalDto).orElseThrow();

    }

    public RentalsDto getAllRentals() {
        Iterable<Rental> allRentals = rentalRepository.findAll();
        ArrayList<RentalDto> listRentalsDto = new ArrayList<>();
        for(Rental rental : allRentals){
            RentalDto rentalDto = rentalMapper.rentalToRentalDto(rental);
            listRentalsDto.add(rentalDto);
        }
        RentalsDto rentalsDto = new RentalsDto();
        rentalsDto.setRentalsDto(listRentalsDto);

        return rentalsDto;
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
            log.info("RentalService : saveRental()");
            rentalRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
