package com.rapido.user_service.controller;

import com.rapido.user_service.dto.UserProfileDTO;
import com.rapido.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserProfileDTO> getProfile() {
        String email = "sai@example.com";
        return ResponseEntity.ok(userService.getProfile(email));
    }

    @PutMapping("/user/profile")
    public ResponseEntity<String> updateProfile(
            @Valid @RequestBody UserProfileDTO dto
    ) {
        String email = dto.getEmail();
        userService.updateProfile(email, dto);
        return ResponseEntity.ok("Profile Updated");
    }
}