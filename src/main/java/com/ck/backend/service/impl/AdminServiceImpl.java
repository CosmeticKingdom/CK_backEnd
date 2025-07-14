package com.ck.backend.service.impl;

import com.ck.backend.dto.*;
import com.ck.backend.entity.User;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.repository.ReservationRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.repository.WaitlistRepository;
import com.ck.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 관리자 서비스 구현체
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final WaitlistRepository waitlistRepository;

    @Override
    public List<UserProfileDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserProfileDto(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return new UserProfileDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    @Override
    @Transactional
    public UserProfileDto updateUser(Long userId, UserProfileUpdateDto userProfileUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setName(userProfileUpdateDto.getName());
        user.setEmail(userProfileUpdateDto.getEmail());

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getRole());
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UserProfileDto updateUserRole(Long userId, UserRoleUpdateDto roleUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setRole(roleUpdateDto.getRole());
        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getRole());
    }

    // Other AdminService methods (reservations, announcements, etc.) remain as TODOs for now

    @Override
    public List<AdminReservationDto> getAllReservations() {
        // TODO: 전체 예약 현황 확인 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public AdminReservationDto approveReservation(Long reservationId) {
        // TODO: 개별 예약 승인 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public AdminReservationDto rejectReservation(Long reservationId) {
        // TODO: 개별 예약 거절 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public Waitlist approveWaitlist(Long waitId) {
        // TODO: 대기자 명단 승인 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public DashboardStatsDto getDashboardStats() {
        // TODO: 통계 대시보드 조회 로직 구현
        return null; // 임시 반환값
    }
}