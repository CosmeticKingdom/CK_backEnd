package com.ck.backend.service.impl;

import com.ck.backend.dto.ServiceDto;
import com.ck.backend.entity.Service;
import com.ck.backend.repository.ServiceRepository;
import com.ck.backend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 서비스(마사지) 서비스 구현체
@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceDto> getAllServices() {
        // TODO: 전체 서비스 목록 조회 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public ServiceDto getServiceById(Long serviceId) {
        // TODO: 개별 서비스 상세 조회 로직 구현
        return null; // 임시 반환값
    }
}