package com.ck.backend.service;

import com.ck.backend.dto.ServiceDto;

import java.util.List;

// 서비스(마사지) 관련 서비스 인터페이스
public interface ServiceService {
    // 전체 서비스 목록 조회
    List<ServiceDto> getAllServices();
    // 개별 서비스 상세 조회
    ServiceDto getServiceById(Long serviceId);
}
