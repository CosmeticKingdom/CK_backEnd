package com.ck.backend.service;

import com.ck.backend.dto.*;
import com.ck.backend.entity.Waitlist;

import java.util.List;

// 관리자 기능 관련 서비스 인터페이스
public interface AdminService {
    // 전체 회원 목록 조회
    List<UserProfileDto> getAllUsers();

    // ID로 특정 사용자 조회
    UserProfileDto getUserById(Long userId);

    // 사용자 정보 업데이트
    UserProfileDto updateUser(Long userId, UserProfileUpdateDto userProfileUpdateDto);

    // 사용자 삭제
    void deleteUser(Long userId);

    // 회원의 권한 변경
    UserProfileDto updateUserRole(Long userId, UserRoleUpdateDto roleUpdateDto);

    // 전체 예약 현황 확인
    List<AdminReservationDto> getAllReservations();

    // 개별 예약 승인
    AdminReservationDto approveReservation(Long reservationId);

    // 개별 예약 거절
    AdminReservationDto rejectReservation(Long reservationId);

    // 대기자 명단 승인
    Waitlist approveWaitlist(Long waitId);

    // 모든 대기자 명단 조회
    List<Waitlist> getAllWaitlist();

    // 통계 대시보드 조회
    DashboardStatsDto getDashboardStats();
}
