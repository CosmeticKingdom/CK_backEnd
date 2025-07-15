package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    private Long serviceId;
    private LocalDate reservationDate;
    private String timeSlot; // 예: "08:00", "08:30"
    // 예약을 위한 기타 필수 필드 추가
}
