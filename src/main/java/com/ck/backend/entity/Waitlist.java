package com.ck.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import com.ck.backend.entity.Massage;
import java.time.LocalDateTime;

@Entity
@Table(name = "waitlist")
@AttributeOverride(name = "id", column = @Column(name = "WAITLIST_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
public class Waitlist extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "MASSAGES_ID")
    private Massage massage;

    @Column(name = "DESIRED_TIME")
    private LocalDateTime desiredTime;

    @Column(name = "STATUS", nullable = false)
    private String status; // 예시: 대기중, 승인됨, 거절됨
}
