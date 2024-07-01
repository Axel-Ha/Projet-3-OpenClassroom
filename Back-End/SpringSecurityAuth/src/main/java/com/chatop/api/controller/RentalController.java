package com.chatop.api.controller;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.domain.entity.Rental;
import com.chatop.api.mapper.RentalMapper;
import com.chatop.api.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    public RentalController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> addNewRental(@RequestBody RentalDto rentalDto){

        Rental rental = rentalMapper.toRental(rentalDto);
        rentalService.saveRental(rental);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental Created");
        return ResponseEntity.ok(msgResponse);
    }
}
