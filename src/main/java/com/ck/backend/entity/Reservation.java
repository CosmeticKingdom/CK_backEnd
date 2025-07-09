package com.ck.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import com.ck.backend.entity.Massage;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@AttributeOverride(name = "id", column = @Column(name = "RESERVATION_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
public class Reservation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "MASSAGES_ID")
    private Massage massage;

    @Column(name = "RESERVATION_TIME")
    private LocalDateTime reservationTime;
    @Column(name = "STATUS")
    private String status; // 예시: 대기중, 확정됨, 취소됨
}
