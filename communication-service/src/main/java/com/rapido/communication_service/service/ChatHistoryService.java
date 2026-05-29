package com.rapido.communication_service.service;

import com.rapido.communication_service.entity.ChatMessage;
import com.rapido.communication_service.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final ChatMessageRepository chatMessageRepository;

    // RIDE CHAT HISTORY
    public List<ChatMessage> getRideMessages(Long rideId) {
        return chatMessageRepository.findByRideId(rideId);
    }

    // UNREAD MESSAGES
    public List<ChatMessage> getUnreadMessages(Long receiverId) {
        return chatMessageRepository.findByReceiverIdAndSeenFalse(receiverId);
    }

    // MARK MESSAGE AS SEEN
    public ChatMessage markAsSeen(Long messageId) {

        ChatMessage message = chatMessageRepository
                .findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setSeen(true);

        return chatMessageRepository.save(message);
    }
}