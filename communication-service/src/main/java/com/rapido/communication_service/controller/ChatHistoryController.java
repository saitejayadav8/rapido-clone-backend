package com.rapido.communication_service.controller;

import com.rapido.communication_service.dto.ChatMessageDTO;
import com.rapido.communication_service.entity.ChatMessage;
import com.rapido.communication_service.service.ChatHistoryService;
import com.rapido.communication_service.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryService chatHistoryService;
    private final ChatMessageService chatMessageService;

    @PostMapping("/send")
    public ChatMessage sendMessage(@RequestBody ChatMessageDTO dto) {
        return chatMessageService.sendMessage(dto);
    }
    @PutMapping("/seen/{messageId}")
    public ChatMessage markAsSeen(@PathVariable Long messageId) {
        return chatHistoryService.markAsSeen(messageId);
    }

    @GetMapping("/history/{rideId}")
    public List<ChatMessage> getRideHistory(@PathVariable Long rideId) {
        return chatHistoryService.getRideMessages(rideId);
    }

    @GetMapping("/unread/{receiverId}")
    public List<ChatMessage> getUnreadMessages(@PathVariable Long receiverId) {
        return chatHistoryService.getUnreadMessages(receiverId);
    }
}