package com.ck.backend.controller;

import com.ck.backend.dto.MassageDto;
import com.ck.backend.service.MassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/massages")
@RequiredArgsConstructor
public class MassageController {

    private final MassageService massageService;

    // 모든 사용자 접근 가능 (마사지 목록 조회)
    @GetMapping
    public ResponseEntity<List<MassageDto>> getAllMassages() {
        List<MassageDto> massages = massageService.getAllMassages();
        return ResponseEntity.ok(massages);
    }

    // 모든 사용자 접근 가능 (특정 마사지 조회)
    @GetMapping("/{id}")
    public ResponseEntity<MassageDto> getMassageById(@PathVariable("id") Long massageId) {
        MassageDto massage = massageService.getMassageById(massageId);
        return ResponseEntity.ok(massage);
    }

    // 관리자만 접근 가능 (마사지 생성)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MassageDto> createMassage(@RequestBody MassageDto massageDto) {
        MassageDto createdMassage = massageService.createMassage(massageDto);
        return new ResponseEntity<>(createdMassage, HttpStatus.CREATED);
    }

    // 관리자만 접근 가능 (마사지 수정)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MassageDto> updateMassage(@PathVariable("id") Long massageId, @RequestBody MassageDto massageDto) {
        MassageDto updatedMassage = massageService.updateMassage(massageId, massageDto);
        return ResponseEntity.ok(updatedMassage);
    }

    // 관리자만 접근 가능 (마사지 삭제)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteMassage(@PathVariable("id") Long massageId) {
        massageService.deleteMassage(massageId);
        return ResponseEntity.noContent().build();
    }
}
