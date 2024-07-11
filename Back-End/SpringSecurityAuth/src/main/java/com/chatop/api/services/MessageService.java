package com.chatop.api.services;

import com.chatop.api.domain.dto.MessageDto;
import com.chatop.api.domain.entity.Message;
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
        Message msg = msgMapper.MsgDtotoMessage(msgDto);
        msg.setCreatedAt(new Date());
        msg.setUpdatedAt(new Date());
        msgRepository.save(msg);
    }
}
