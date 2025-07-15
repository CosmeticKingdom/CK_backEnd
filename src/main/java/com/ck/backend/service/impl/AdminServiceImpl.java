package com.ck.backend.service.impl;

import com.ck.backend.dto.*;
import com.ck.backend.entity.Reservation;
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
                .map(user -> new UserProfileDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return new UserProfileDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getRole());
    }

    @Override
    @Transactional
    public UserProfileDto updateUser(Long userId, UserProfileUpdateDto userProfileUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (userProfileUpdateDto.getName() != null) {
            user.setName(userProfileUpdateDto.getName());
        }
        if (userProfileUpdateDto.getEmail() != null) {
            user.setEmail(userProfileUpdateDto.getEmail());
        }
        if (userProfileUpdateDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userProfileUpdateDto.getPhoneNumber());
        }

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getPhoneNumber(), updatedUser.getEmail(), updatedUser.getRole());
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
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getPhoneNumber(), updatedUser.getEmail(), updatedUser.getRole());
    }

    // Other AdminService methods (reservations, announcements, etc.) remain as TODOs for now

    @Override
    public List<AdminReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToAdminReservationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdminReservationDto approveReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

        // 현재 예약의 상태를 CONFIRMED로 변경
        reservation.setStatus("CONFIRMED");
        Reservation updatedReservation = reservationRepository.save(reservation);

        // 동일 사용자의 동일 시간대 다른 PENDING 예약을 CANCELLED로 변경
        List<Reservation> conflictingReservations = reservationRepository.findByUserAndReservationTimeAndStatusAndIdNot(
                reservation.getUser(),
                reservation.getReservationTime(),
                "PENDING",
                reservation.getId()
        );

        for (Reservation conflict : conflictingReservations) {
            conflict.setStatus("CANCELLED");
            reservationRepository.save(conflict);
        }

        return convertToAdminReservationDto(updatedReservation);
    }

    @Override
    @Transactional
    public AdminReservationDto rejectReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
        reservation.setStatus("REJECTED");
        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToAdminReservationDto(updatedReservation);
    }

    private AdminReservationDto convertToAdminReservationDto(Reservation reservation) {
        return new AdminReservationDto(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getUser().getName(),
                reservation.getMassage().getId(),
                reservation.getMassage().getName(),
                reservation.getReservationTime(),
                reservation.getStatus()
        );
    }

    @Override
    @Transactional
    public Waitlist approveWaitlist(Long waitId) {
        Waitlist waitlist = waitlistRepository.findById(waitId)
                .orElseThrow(() -> new RuntimeException("Waitlist entry not found with id: " + waitId));

        // 대기자 명단 정보를 바탕으로 예약 생성
        Reservation newReservation = Reservation.builder()
                .user(waitlist.getUser())
                .massage(waitlist.getMassage())
                .reservationTime(waitlist.getDesiredTime()) // 대기자가 원하는 시간으로 예약
                .status("CONFIRMED") // 바로 확정 상태로
                .build();

        reservationRepository.save(newReservation);

        // 대기자 명단 상태 변경 (예: APPROVED 또는 삭제)
        waitlist.setStatus("APPROVED"); // 대기자 명단 상태를 APPROVED로 변경
        waitlistRepository.save(waitlist);

        return waitlist; // 승인된 대기자 명단 반환
    }

    @Override
    public DashboardStatsDto getDashboardStats() {
        // TODO: 통계 대시보드 조회 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public List<Waitlist> getAllWaitlist() {
        return waitlistRepository.findAll();
    }
}