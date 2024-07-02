package com.chatop.api.controller;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/rentals")
@RestController
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createRental(RentalDto rentalDto){
        rentalService.saveRental(rentalDto);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental created");
        return ResponseEntity.ok(msgResponse);
    }

}
