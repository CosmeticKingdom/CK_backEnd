package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementRequestDto {
    private String title;
    private String content;
    // 만료일, 유형 등 다른 필드 추가
}
