package com.ck.backend.controller;

import com.ck.backend.dto.*;
import com.ck.backend.service.AdminService;
import com.ck.backend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final AnnouncementService announcementService;

    @GetMapping("/hello")
    public ResponseEntity<String> adminHello() {
        return ResponseEntity.ok("안녕하세요 관리자님! 관리자 리소스에 접속하셨습니다.");
    }

    // User Management Endpoints
    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        List<UserProfileDto> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileDto> getUserById(@PathVariable("id") Long userId) {
        UserProfileDto user = adminService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserProfileDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserProfileUpdateDto userProfileUpdateDto) {
        UserProfileDto updatedUser = adminService.updateUser(userId, userProfileUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserProfileDto> updateUserRole(@PathVariable("id") Long userId, @RequestBody UserRoleUpdateDto roleUpdateDto) {
        UserProfileDto updatedUser = adminService.updateUserRole(userId, roleUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    // Announcement Management Endpoints
    @PostMapping("/announcements")
    public ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementRequestDto requestDto) {
        AnnouncementDto createdAnnouncement = announcementService.createAnnouncement(requestDto);
        return new ResponseEntity<>(createdAnnouncement, HttpStatus.CREATED);
    }

    @GetMapping("/announcements")
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements() {
        List<AnnouncementDto> announcements = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/announcements/{id}")
    public ResponseEntity<AnnouncementDto> getAnnouncementById(@PathVariable("id") Long announcementId) {
        AnnouncementDto announcement = announcementService.getAnnouncementById(announcementId);
        return ResponseEntity.ok(announcement);
    }

    @PutMapping("/announcements/{id}")
    public ResponseEntity<AnnouncementDto> updateAnnouncement(@PathVariable("id") Long announcementId, @RequestBody AnnouncementRequestDto requestDto) {
        AnnouncementDto updatedAnnouncement = announcementService.updateAnnouncement(announcementId, requestDto);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/announcements/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return ResponseEntity.noContent().build();
    }
}