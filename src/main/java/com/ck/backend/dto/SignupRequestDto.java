package com.ck.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String loginId;
    private String password;
    private String email;
    private String role;
    // 회원가입에 필요한 다른 필드 추가
}
