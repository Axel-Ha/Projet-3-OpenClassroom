package com.chatop.api.controller;

import com.chatop.api.domain.dto.MessageDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService msgService;

    public MessageController(MessageService msgService){
        this.msgService = msgService;
    }

    @PostMapping()
    @Operation(
            summary = "Send a message to the owner of a rental",
            description = "Save a message sent by an user to the owner of the rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The message was successfully sent"),
            @ApiResponse(responseCode = "400", description = "Bad request, possibly due to validation errors"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<MessageResponse> createNewMessage(@RequestBody MessageDto messageDto){
        MessageResponse msgResponse = new MessageResponse();
        msgService.saveMessage(messageDto);
        msgResponse.setMessage("Message was sent with success");
        return ResponseEntity.ok(msgResponse);
    }
}
