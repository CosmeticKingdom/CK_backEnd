package com.ck.backend.controller;

import com.ck.backend.dto.ServiceDto;
import com.ck.backend.service.ServiceService; // 인터페이스로 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/")
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long serviceId) {
        ServiceDto service = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }
}
