package com.rapido.communication_service.service;

import com.rapido.communication_service.dto.ChatMessageDTO;
import com.rapido.communication_service.entity.ChatMessage;
import com.rapido.communication_service.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage sendMessage(ChatMessageDTO dto) {

        ChatMessage message = new ChatMessage();

        message.setRideId(dto.getRideId());
        message.setSenderId(dto.getSenderId());
        message.setReceiverId(dto.getReceiverId());
        message.setMessage(dto.getMessage());

        message.setDelivered(true);
        message.setSeen(false);
        message.setTimestamp(LocalDateTime.now());

        return chatMessageRepository.save(message);
    }
}