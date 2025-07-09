package com.ck.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import com.ck.backend.entity.Massage;
import java.time.LocalDateTime;

@Entity
@Table(name = "waitlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waitlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Massage massage;

    private LocalDateTime requestTime;
    private String status; // 예시: 대기중, 승인됨, 거절됨

    @PrePersist
    protected void onCreate() {
        requestTime = LocalDateTime.now();
    }
}
