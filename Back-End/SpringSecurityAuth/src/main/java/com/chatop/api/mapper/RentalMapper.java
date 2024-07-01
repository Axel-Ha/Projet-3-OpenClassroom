package com.chatop.api.mapper;

import com.chatop.api.domain.dto.RentalDto;
import com.chatop.api.domain.entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public RentalDto rentalToRentalDto(Rental rental){
        if(rental == null){ return null; }
        else{
            RentalDto rentalDto = new RentalDto();
            rentalDto.setId(rental.getId());
            rentalDto.setName(rental.getName());
            rentalDto.setSurface(rental.getSurface());
            rentalDto.setPrice(rental.getPrice());
            rentalDto.setPicture(rental.getPicture());
            rentalDto.setOwnerId(rental.getOwnerId());
            return rentalDto;
        }
    }

    public Rental toRental(RentalDto rentalDto){
        if(rentalDto == null){ return null; }
        else{
            Rental rental = new Rental();
            rental.setId(rentalDto.getId());
            rental.setName(rentalDto.getName());
            rental.setSurface(rentalDto.getSurface());
            rental.setPrice(rentalDto.getPrice());
            rental.setPicture(rentalDto.getPicture());
            rental.setOwnerId(rentalDto.getOwnerId());
            rental.setCreatedAt(rentalDto.getCreatedAt());
            rental.setUpdatedAt(rentalDto.getUpdatedAt());
            return rental;
        }
    }
}