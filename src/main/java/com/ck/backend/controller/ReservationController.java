package com.ck.backend.controller;

import com.ck.backend.dto.ReservationDto;
import com.ck.backend.dto.ReservationRequestDto;
import com.ck.backend.dto.WaitlistRequestDto;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.service.ReservationService;
import com.ck.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    private Long getUserIdFromAuthentication(Authentication authentication) {
        String loginId = authentication.getName();
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found with loginId: " + loginId))
                .getId();
    }

    // 예약 생성 (ROLE_USER만 가능)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReservationDto> createReservation(Authentication authentication, @RequestBody ReservationRequestDto requestDto) {
        Long userId = getUserIdFromAuthentication(authentication);
        ReservationDto newReservation = reservationService.createReservation(userId, requestDto);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    // 사용자 자신의 예약 목록 조회 (ROLE_USER만 가능)
    @GetMapping("/my")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReservationDto>> getUserReservations(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<ReservationDto> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    // 특정 예약 상세 조회 (ROLE_USER 또는 ROLE_ADMIN 가능)
    @GetMapping("/{reservationId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ReservationDto> getReservationById(Authentication authentication, @PathVariable Long reservationId) {
        Long userId = getUserIdFromAuthentication(authentication);
        ReservationDto reservation = reservationService.getReservationById(reservationId, userId); // 사용자 ID도 함께 전달
        return ResponseEntity.ok(reservation);
    }

    // 예약 취소 (ROLE_USER만 가능)
    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> cancelReservation(Authentication authentication, @PathVariable Long reservationId) {
        Long userId = getUserIdFromAuthentication(authentication);
        reservationService.cancelReservation(reservationId, userId); // 사용자 ID도 함께 전달
        return ResponseEntity.noContent().build();
    }

    // 대기자 명단 추가 (ROLE_USER만 가능)
    @PostMapping("/{serviceId}/wait")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Waitlist> addToWaitlist(Authentication authentication, @PathVariable Long serviceId, @RequestBody WaitlistRequestDto requestDto) {
        Long userId = getUserIdFromAuthentication(authentication);
        requestDto.setServiceId(serviceId); // 경로에서 serviceId를 사용하도록 보장
        Waitlist waitlistEntry = reservationService.addToWaitlist(userId, requestDto);
        return new ResponseEntity<>(waitlistEntry, HttpStatus.CREATED);
    }
}
