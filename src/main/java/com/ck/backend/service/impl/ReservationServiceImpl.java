package com.ck.backend.service.impl;

import com.ck.backend.dto.ReservationDto;
import com.ck.backend.dto.ReservationRequestDto;
import com.ck.backend.dto.WaitlistRequestDto;
import com.ck.backend.entity.Reservation;
import com.ck.backend.entity.User;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.repository.ReservationRepository;
import com.ck.backend.repository.MassageRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.repository.WaitlistRepository;
import com.ck.backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 예약 서비스 구현체
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final MassageRepository massageRepository;
    private final WaitlistRepository waitlistRepository;

    @Override
    public ReservationDto createReservation(Long userId, ReservationRequestDto requestDto) {
        // TODO: 예약 신청 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public List<ReservationDto> getUserReservations(Long userId) {
        // TODO: 사용자 본인의 모든 예약 조회 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public ReservationDto getReservationById(Long reservationId) {
        // TODO: 개별 예약 확인 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public void cancelReservation(Long reservationId) {
        // TODO: 예약 취소 로직 구현
    }

    @Override
    public Waitlist addToWaitlist(Long userId, WaitlistRequestDto requestDto) {
        // TODO: 대기자 명단 신청 로직 구현
        return null; // 임시 반환값
    }
}