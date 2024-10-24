package com.chatop.api.services;

import com.chatop.api.domain.dto.MessageDto;
import com.chatop.api.domain.entity.Message;
import com.chatop.api.exceptions.MessageErrorException;
import com.chatop.api.mapper.MessageMapper;
import com.chatop.api.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class MessageService {

    private final MessageMapper msgMapper;
    private final MessageRepository msgRepository;

    public MessageService(MessageMapper msgMapper, MessageRepository msgRepository) {
        this.msgMapper = msgMapper;
        this.msgRepository = msgRepository;
    }

    public void saveMessage(MessageDto msgDto){
        try{
            // Convert the MessageDto to a Message entity using msgMapper
            Message msg = msgMapper.MsgDtotoMessage(msgDto);
            msg.setCreatedAt(new Date());
            msg.setUpdatedAt(new Date());
            // Save the Message entity to the repository
            msgRepository.save(msg);
        }
        catch (Exception e){
            throw new MessageErrorException("Message could not be save",e);
        }
    }
}
