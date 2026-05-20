package com.rapido.user_service.service;

import com.rapido.user_service.dto.UserProfileRequest;
import com.rapido.user_service.entity.UserProfile;
import com.rapido.user_service.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile createProfile(UserProfileRequest request) {

        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User profile already exists with this email");
        }

        UserProfile profile = new UserProfile();
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setProfileImage(request.getProfileImage());

        return userProfileRepository.save(profile);
    }

    public UserProfile getProfileByEmail(String email) {
        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
    }

    public UserProfile updateProfile(String email, UserProfileRequest request) {

        UserProfile profile = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User profile not found"));

        profile.setFullName(request.getFullName());
        profile.setPhone(request.getPhone());
        profile.setProfileImage(request.getProfileImage());

        return userProfileRepository.save(profile);
    }
}