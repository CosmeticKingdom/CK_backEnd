package com.ck.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
@AttributeOverride(name = "id", column = @Column(name = "ANNOUNCEMENT_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BaseEntity {
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content; // 공지사항 내용
}
