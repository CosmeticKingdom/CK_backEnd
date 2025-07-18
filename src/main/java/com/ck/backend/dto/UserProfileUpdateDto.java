package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {
    private String name;
    private String email;
    private String phoneNumber;
    // 다른 업데이트 가능한 필드 추가
}
