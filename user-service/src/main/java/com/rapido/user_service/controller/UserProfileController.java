package com.rapido.user_service.controller;

import com.rapido.user_service.dto.UserProfileRequest;
import com.rapido.user_service.entity.UserProfile;
import com.rapido.user_service.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("User Service Working");
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> createProfile(
            @Valid @RequestBody UserProfileRequest request
    ) {
        return ResponseEntity.ok(userProfileService.createProfile(request));
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserProfile> getProfile(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(userProfileService.getProfileByEmail(email));
    }

    @PutMapping("/profile/{email}")
    public ResponseEntity<UserProfile> updateProfile(
            @PathVariable String email,
            @Valid @RequestBody UserProfileRequest request
    ) {
        return ResponseEntity.ok(userProfileService.updateProfile(email, request));
    }
}