package com.chatop.api.mapper;

import com.chatop.api.domain.dto.MessageDto;
import com.chatop.api.domain.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDto MsgtoDto(Message message) {
        if (message == null) {
            return null;
        }

        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setRental_id(message.getRentalId());
        messageDto.setUser_id(message.getUserId());
        messageDto.setMessage(message.getMessage());
        messageDto.setCreatedAt(message.getCreatedAt());
        messageDto.setUpdatedAt(message.getUpdatedAt());

        return messageDto;
    }

    public Message MsgDtotoMessage(MessageDto messageDto) {
        if (messageDto == null) {
            return null;
        }

        Message message = new Message();
        message.setId(messageDto.getId());
        message.setRentalId(messageDto.getRental_id());
        message.setUserId(messageDto.getUser_id());
        message.setMessage(messageDto.getMessage());
        message.setCreatedAt(messageDto.getCreatedAt());
        message.setUpdatedAt(messageDto.getUpdatedAt());

        return message;
    }
}