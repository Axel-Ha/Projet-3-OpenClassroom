package com.chatop.api.controller;

import com.chatop.api.domain.dto.MessageDto;
import com.chatop.api.domain.entity.MessageResponse;
import com.chatop.api.services.MessageService;
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
    public ResponseEntity<MessageResponse> createNewMessage(@RequestBody MessageDto messageDto){
        MessageResponse msgResponse = new MessageResponse();
        log.info("MessageController: createNewMessage: " + messageDto);
        msgService.saveMessage(messageDto);
        msgResponse.setMessage("Message was sent with success");
        return ResponseEntity.ok(msgResponse);
    }
}
