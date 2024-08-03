package com.chatop.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.dto.RentalsDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.RentalService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.chatop.api.configuration.SwaggerConfig.NAME_SECURITY_REQUIREMENT;

@Slf4j
@RequestMapping("/api")
@RestController
@SecurityRequirement(name=NAME_SECURITY_REQUIREMENT)
public class RentalController {
    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/rentals/{id}")
    @Operation(
            summary = "Get rental by ID",
            description = "Retrieve a rental by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rental"),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    public ResponseEntity<RentalDto> getRental(@PathVariable final Long id){
        return ResponseEntity.ok(rentalService.getRental(id));
    }

    @GetMapping("/rentals")
    @Operation(
            summary = "Get all rentals",
            description = "Permit to retrieve all the rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rentals"),
            @ApiResponse(responseCode = "404", description = "Rentals not found")
    })
    public ResponseEntity<RentalsDto> getAllRentals(){
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @PostMapping("/rentals")
    @Operation(
            summary = "Create a rental",
            description = "Permit to create a rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MessageResponse> createRental(
            @RequestParam String name,
            @RequestParam int surface,
            @RequestParam int price,
            @RequestParam MultipartFile picture,
            @RequestParam String description
    ) throws IOException {
        rentalService.saveRental(name, surface, price, picture, description);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental created");
        return ResponseEntity.ok(msgResponse);
    }

    @PutMapping("/rentals/{id}")
    @Operation(
            summary = "Update a rental",
            description = "Update a rental with its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<MessageResponse> updateRental(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam int surface,
            @RequestParam int price,
            @RequestParam String description
    ){
        rentalService.updateRental(id, name, surface, price, description);
        MessageResponse msgResponse = new MessageResponse();
        msgResponse.setMessage("Rental updated");
        return ResponseEntity.ok(msgResponse);
    }

}
