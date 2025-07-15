package com.ck.backend.service.impl;

import com.ck.backend.dto.ReservationDto;
import com.ck.backend.dto.ReservationRequestDto;
import com.ck.backend.dto.WaitlistRequestDto;
import com.ck.backend.entity.Reservation;
import com.ck.backend.entity.User;
import com.ck.backend.entity.Massage;
import com.ck.backend.entity.Waitlist;
import com.ck.backend.exception.ReservationConflictException;
import com.ck.backend.repository.ReservationRepository;
import com.ck.backend.repository.MassageRepository;
import com.ck.backend.repository.UserRepository;
import com.ck.backend.repository.WaitlistRepository;
import com.ck.backend.service.ReservationService;
import com.ck.backend.util.TimeSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

// 예약 서비스 구현체
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final MassageRepository massageRepository;
    private final WaitlistRepository waitlistRepository;

    @Override
    @Transactional
    public ReservationDto createReservation(Long userId, ReservationRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Massage massage = massageRepository.findById(requestDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Massage not found with id: " + requestDto.getServiceId()));

        // TimeSlot enum을 사용하여 예약 시간 생성
        TimeSlot timeSlot = TimeSlot.fromDisplayTime(requestDto.getTimeSlot());
        LocalDateTime reservationStartDateTime = requestDto.getReservationDate().atTime(timeSlot.getLocalTime());

        // 예약 종료 시간 계산
        LocalDateTime reservationEndDateTime = reservationStartDateTime.plus(Duration.ofMinutes(massage.getDurationMinutes()));

        // 기존 확정된 예약과의 충돌 검사 (서비스 레이어에서 처리)
        LocalDateTime startOfDay = requestDto.getReservationDate().atStartOfDay();
        LocalDateTime endOfDay = requestDto.getReservationDate().atTime(23, 59, 59);

        List<Reservation> existingConfirmedReservations = reservationRepository.findByStatusAndReservationTimeBetween(
                "CONFIRMED",
                startOfDay,
                endOfDay
        );

        for (Reservation existingReservation : existingConfirmedReservations) {
            LocalDateTime existingStart = existingReservation.getReservationTime();
            LocalDateTime existingEnd = existingStart.plus(Duration.ofMinutes(existingReservation.getMassage().getDurationMinutes()));

            // Check for overlap: (start1 < end2 && start2 < end1)
            if (reservationStartDateTime.isBefore(existingEnd) && existingStart.isBefore(reservationEndDateTime)) {
                throw new ReservationConflictException("Reservation time conflicts with an existing confirmed reservation.");
            }
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .massage(massage)
                .reservationTime(reservationStartDateTime)
                .status("PENDING") // 초기 상태는 PENDING
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDto(savedReservation);
    }

    @Override
    public List<ReservationDto> getUserReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return reservationRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

        // 예약의 소유자인지 확인
        if (!reservation.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to reservation");
        }

        return convertToDto(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

        // 예약의 소유자인지 확인
        if (!reservation.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to reservation");
        }

        reservation.setStatus("CANCELLED"); // 상태를 CANCELLED로 변경
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Waitlist addToWaitlist(Long userId, WaitlistRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Massage massage = massageRepository.findById(requestDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Massage not found with id: " + requestDto.getServiceId()));

        Waitlist waitlist = Waitlist.builder()
                .user(user)
                .massage(massage)
                .desiredTime(requestDto.getDesiredTime())
                .status("PENDING") // 대기자 명단 초기 상태는 PENDING
                .build();

        return waitlistRepository.save(waitlist);
    }

    private ReservationDto convertToDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getMassage().getId(),
                reservation.getReservationTime(),
                reservation.getStatus()
        );
    }

    // Waitlist 엔티티를 DTO로 변환하는 헬퍼 메소드 (필요시 추가)
    // private WaitlistDto convertToWaitlistDto(Waitlist waitlist) {
    //     return new WaitlistDto(
    //             waitlist.getId(),
    //             waitlist.getUser().getId(),
    //             waitlist.getMassage().getId(),
    //             waitlist.getDesiredTime(),
    //             waitlist.getStatus()
    //     );
    // }
}