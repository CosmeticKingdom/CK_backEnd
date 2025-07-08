package com.ck.backend.controller;

import com.ck.backend.dto.AdminReservationDto;
import com.ck.backend.dto.AnnouncementDto;
import com.ck.backend.dto.AnnouncementRequestDto;
import com.ck.backend.dto.DashboardStatsDto;
import com.ck.backend.dto.UserRoleUpdateDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.service.AdminService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        List<UserProfileDto> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<UserProfileDto> updateUserRole(@PathVariable Long userId, @RequestBody UserRoleUpdateDto roleUpdateDto) {
        UserProfileDto updatedUser = adminService.updateUserRole(userId, roleUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<AdminReservationDto>> getAllReservations() {
        List<AdminReservationDto> reservations = adminService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations/{reservationId}/approve")
    public ResponseEntity<AdminReservationDto> approveReservation(@PathVariable Long reservationId) {
        AdminReservationDto approvedReservation = adminService.approveReservation(reservationId);
        return ResponseEntity.ok(approvedReservation);
    }

    @PostMapping("/reservations/{reservationId}/reject")
    public ResponseEntity<AdminReservationDto> rejectReservation(@PathVariable Long reservationId) {
        AdminReservationDto rejectedReservation = adminService.rejectReservation(reservationId);
        return ResponseEntity.ok(rejectedReservation);
    }

    @PostMapping("/waitlist/{waitId}/approve")
    public ResponseEntity<Waitlist> approveWaitlist(@PathVariable Long waitId) {
        Waitlist approvedWaitlist = adminService.approveWaitlist(waitId);
        return ResponseEntity.ok(approvedWaitlist);
    }

    @PostMapping("/announcements")
    public ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementRequestDto requestDto) {
        AnnouncementDto newAnnouncement = adminService.createAnnouncement(requestDto);
        return new ResponseEntity<>(newAnnouncement, HttpStatus.CREATED);
    }

    @PutMapping("/announcements/{announcementId}")
    public ResponseEntity<AnnouncementDto> updateAnnouncement(@PathVariable Long announcementId, @RequestBody AnnouncementRequestDto requestDto) {
        AnnouncementDto updatedAnnouncement = adminService.updateAnnouncement(announcementId, requestDto);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/announcements/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long announcementId) {
        adminService.deleteAnnouncement(announcementId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStatsDto> getDashboardStats() {
        DashboardStatsDto stats = adminService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }
}
