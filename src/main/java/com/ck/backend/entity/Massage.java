package com.ck.backend.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "massage")
@AttributeOverride(name = "id", column = @Column(name = "MASSAGE_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
public class Massage extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "DURATION_MINUTES")
    private int durationMinutes;
    // 다른 서비스 관련 필드 추가
}
