package com.ck.backend.repository;

import com.ck.backend.entity.Reservation;
import com.ck.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 사용자 ID로 예약 목록 찾기
    List<Reservation> findByUser(User user);

    // 특정 사용자의 특정 시간대에 있는 PENDING 상태의 다른 예약 찾기
    List<Reservation> findByUserAndReservationTimeAndStatusAndIdNot(User user, LocalDateTime reservationTime, String status, Long reservationId);

    // 주어진 시간 범위 내의 CONFIRMED 상태 예약 찾기 (정확한 충돌 검사는 서비스 레이어에서)
    List<Reservation> findByStatusAndReservationTimeBetween(String status, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Reservation> findByUserIdAndMassageId(Long userId, Long massageId);
}
