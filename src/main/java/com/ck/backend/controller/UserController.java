package com.ck.backend.controller;

import com.ck.backend.dto.NotificationSettingRequestDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.dto.UserProfileUpdateDto;
import com.ck.backend.entity.UserNotificationSetting;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.service.UserService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private Long getUserIdFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile() {
        Long userId = getUserIdFromContext();
        UserProfileDto profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody UserProfileUpdateDto updateDto) {
        Long userId = getUserIdFromContext();
        UserProfileDto updatedProfile = userService.updateUserProfile(userId, updateDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteUser() {
        Long userId = getUserIdFromContext();
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<UserNotificationSetting> updateNotificationSettings(@RequestBody NotificationSettingRequestDto settingsDto) {
        Long userId = getUserIdFromContext();
        UserNotificationSetting updatedSettings = userService.updateNotificationSettings(userId, settingsDto);
        return ResponseEntity.ok(updatedSettings);
    }
}
