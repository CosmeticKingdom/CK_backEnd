package com.ck.backend.service;

import com.ck.backend.dto.MassageDto;

import java.util.List;

// 서비스(마사지) 관련 서비스 인터페이스
public interface MassageService {
    // 전체 서비스 목록 조회
    List<MassageDto> getAllMassages();
    // 개별 서비스 상세 조회
    MassageDto getMassageById(Long massageId);
    // 마사지 생성
    MassageDto createMassage(MassageDto massageDto);
    // 마사지 수정
    MassageDto updateMassage(Long massageId, MassageDto massageDto);
    // 마사지 삭제
    void deleteMassage(Long massageId);
}
