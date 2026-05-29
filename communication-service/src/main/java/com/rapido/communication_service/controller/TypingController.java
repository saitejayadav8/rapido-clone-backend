package com.rapido.communication_service.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/typing")
public class TypingController {

    @PostMapping("/start")
    public Map<String, Object> startTyping(@RequestParam Long rideId,
                                           @RequestParam Long userId) {

        Map<String, Object> response = new HashMap<>();
        response.put("rideId", rideId);
        response.put("userId", userId);
        response.put("typing", true);
        response.put("message", "User is typing...");
        response.put("timestamp", LocalDateTime.now());

        return response;
    }

    @PostMapping("/stop")
    public Map<String, Object> stopTyping(@RequestParam Long rideId,
                                          @RequestParam Long userId) {

        Map<String, Object> response = new HashMap<>();
        response.put("rideId", rideId);
        response.put("userId", userId);
        response.put("typing", false);
        response.put("message", "User stopped typing");
        response.put("timestamp", LocalDateTime.now());

        return response;
    }
}