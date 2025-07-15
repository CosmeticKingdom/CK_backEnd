package com.ck.backend.service;

import com.ck.backend.dto.ReservationDto;
import com.ck.backend.dto.ReservationRequestDto;
import com.ck.backend.dto.WaitlistRequestDto;
import com.ck.backend.entity.Waitlist;

import java.util.List;

// 예약 관련 서비스 인터페이스
public interface ReservationService {
    // 예약 신청
    ReservationDto createReservation(Long userId, ReservationRequestDto requestDto);
    // 사용자 본인의 모든 예약 조회
    List<ReservationDto> getUserReservations(Long userId);
    // 개별 예약 확인
    ReservationDto getReservationById(Long reservationId, Long userId);
    // 예약 취소
    void cancelReservation(Long reservationId, Long userId);
    // 대기자 명단 신청
    Waitlist addToWaitlist(Long userId, WaitlistRequestDto requestDto);
}
