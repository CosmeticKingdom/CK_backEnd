package com.ck.backend.service.impl;

import com.ck.backend.dto.MassageDto;

import com.ck.backend.repository.MassageRepository;
import com.ck.backend.service.MassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 서비스(마사지) 서비스 구현체
@Service
@RequiredArgsConstructor
public class MassageServiceImpl implements MassageService {

    private final MassageRepository massageRepository;

    @Override
    public List<MassageDto> getAllMassages() {
        // TODO: 전체 서비스 목록 조회 로직 구현
        return null; // 임시 반환값
    }

    @Override
    public MassageDto getMassageById(Long massageId) {
        // TODO: 개별 서비스 상세 조회 로직 구현
        return null; // 임시 반환값
    }
}