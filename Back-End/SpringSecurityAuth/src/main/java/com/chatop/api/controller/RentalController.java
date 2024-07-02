package com.chatop.api.controller;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequestMapping("/api")
@RestController
public class RentalController {
    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rentals")
    public ResponseEntity<MessageResponse> createRental(RentalDto rentalDto) throws IOException {
        log.info("RentalController : createRental()");
        rentalService.saveRental(rentalDto);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental created");
        return ResponseEntity.ok(msgResponse);
    }

}
