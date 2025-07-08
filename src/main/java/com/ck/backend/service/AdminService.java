package com.ck.backend.service;

import com.ck.backend.dto.AdminReservationDto;
import com.ck.backend.dto.AnnouncementDto;
import com.ck.backend.dto.AnnouncementRequestDto;
import com.ck.backend.dto.DashboardStatsDto;
import com.ck.backend.dto.UserRoleUpdateDto;
import com.ck.backend.dto.UserProfileDto;
import com.ck.backend.entity.Waitlist;

import java.util.List;

// 관리자 기능 관련 서비스 인터페이스
public interface AdminService {
    // 전체 회원 목록 조회
    List<UserProfileDto> getAllUsers();
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
    // 공지사항 등록
    AnnouncementDto createAnnouncement(AnnouncementRequestDto requestDto);
    // 공지사항 수정
    AnnouncementDto updateAnnouncement(Long announcementId, AnnouncementRequestDto requestDto);
    // 공지사항 삭제
    void deleteAnnouncement(Long announcementId);
    // 통계 대시보드 조회
    DashboardStatsDto getDashboardStats();
}
