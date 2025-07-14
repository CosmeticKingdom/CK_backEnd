package com.ck.backend.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "announcement")
@AttributeOverride(name = "id", column = @Column(name = "ANNOUNCEMENT_ID"))

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BaseEntity {
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content; // 공지사항 내용
}
