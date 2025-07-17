package com.ck.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import com.ck.backend.entity.Massage;
import com.ck.backend.util.Rating;

import java.time.LocalDateTime;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "REVIEW_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "review")
public class Review extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "MASSAGE_ID")
    private Massage massage;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "RATING")
    @Enumerated(EnumType.STRING)
    private Rating rating; // 평점 (예: 1-5점)
}
