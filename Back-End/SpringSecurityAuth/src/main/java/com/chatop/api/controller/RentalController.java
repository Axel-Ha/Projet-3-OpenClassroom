package com.chatop.api.controller;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.dto.RentalsDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/rentals/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable final Long id){
        log.info("RentalController : getRental()");
        return ResponseEntity.ok(rentalService.getRental(id));


    }

    @GetMapping("/rentals")
    public ResponseEntity<RentalsDto> getAllRentals(){
        log.info("RentalController : getAllRentals()");
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @PostMapping("/rentals")
    public ResponseEntity<MessageResponse> createRental(
            @RequestParam String name,
            @RequestParam int surface,
            @RequestParam int price,
            @RequestParam MultipartFile picture,
            @RequestParam String description

            ) throws IOException {
        log.info("RentalController : createRental()");
        rentalService.saveRental(name, surface, price, picture, description);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental created");
        return ResponseEntity.ok(msgResponse);
    }

    @PutMapping("/rentals/{id}")
    public ResponseEntity<MessageResponse> updateRental(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam int surface,
            @RequestParam int price,
            @RequestParam String description
    ){
        rentalService.updateRental(id, name,surface,price,description);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental updated");
        return ResponseEntity.ok(msgResponse);
    }
}
