package com.ck.backend.controller;

import com.ck.backend.dto.MassageDto;
import com.ck.backend.service.MassageService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/massages")
public class MassageController {

    @Autowired
    private MassageService massageService;

    @GetMapping("/")
    public ResponseEntity<List<MassageDto>> getAllMassages() {
        List<MassageDto> massages = massageService.getAllMassages();
        return ResponseEntity.ok(massages);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<MassageDto> getMassageById(@PathVariable Long massageId) {
        MassageDto massage = massageService.getMassageById(massageId);
        return ResponseEntity.ok(massage);
    }
}
