package com.ck.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> adminHello() {
        return ResponseEntity.ok("안녕하세요 관리자님! 관리자 리소스에 접속하셨습니다.");
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAllUsers() {
        // TODO: Implement logic to fetch all users (admin only)
        return ResponseEntity.ok("admin만이 갈 수 있는 endpoint 입니다.");
    }
}