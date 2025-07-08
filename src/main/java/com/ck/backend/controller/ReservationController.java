package com.ck.backend.controller;

import com.ck.backend.dto.ReservationDto;
import com.ck.backend.dto.ReservationRequestDto;
import com.ck.backend.dto.WaitlistRequestDto;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.service.ReservationService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 실제 애플리케이션에서는 보안 컨텍스트에서 사용자 ID를 추출해야 합니다.
    // 현재는 플레이스홀더 userId를 사용합니다.
    private Long getUserIdFromContext() {
        // 인증된 사용자의 ID를 가져오는 실제 로직으로 대체되어야 합니다.
        return 1L; 
    }

    @PostMapping("/")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequestDto requestDto) {
        Long userId = getUserIdFromContext();
        ReservationDto newReservation = reservationService.createReservation(userId, requestDto);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationDto>> getUserReservations() {
        Long userId = getUserIdFromContext();
        List<ReservationDto> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long reservationId) {
        ReservationDto reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{serviceId}/wait")
    public ResponseEntity<Waitlist> addToWaitlist(@PathVariable Long serviceId, @RequestBody WaitlistRequestDto requestDto) {
        Long userId = getUserIdFromContext();
        requestDto.setServiceId(serviceId); // 경로에서 serviceId를 사용하도록 보장
        Waitlist waitlistEntry = reservationService.addToWaitlist(userId, requestDto);
        return new ResponseEntity<>(waitlistEntry, HttpStatus.CREATED);
    }
}
