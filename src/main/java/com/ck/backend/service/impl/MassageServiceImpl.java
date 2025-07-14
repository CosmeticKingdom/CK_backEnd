package com.ck.backend.service.impl;

import com.ck.backend.dto.MassageDto;
import com.ck.backend.entity.Massage;
import com.ck.backend.repository.MassageRepository;
import com.ck.backend.service.MassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 서비스(마사지) 서비스 구현체
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MassageServiceImpl implements MassageService {

    private final MassageRepository massageRepository;

    @Override
    public List<MassageDto> getAllMassages() {
        return massageRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MassageDto getMassageById(Long massageId) {
        Massage massage = massageRepository.findById(massageId)
                .orElseThrow(() -> new RuntimeException("Massage not found with id: " + massageId));
        return convertToDto(massage);
    }

    @Override
    @Transactional
    public MassageDto createMassage(MassageDto massageDto) {
        Massage massage = convertToEntity(massageDto);
        Massage savedMassage = massageRepository.save(massage);
        return convertToDto(savedMassage);
    }

    @Override
    @Transactional
    public MassageDto updateMassage(Long massageId, MassageDto massageDto) {
        Massage existingMassage = massageRepository.findById(massageId)
                .orElseThrow(() -> new RuntimeException("Massage not found with id: " + massageId));

        if (massageDto.getName() != null) {
            existingMassage.setName(massageDto.getName());
        }
        if (massageDto.getDescription() != null) {
            existingMassage.setDescription(massageDto.getDescription());
        }
        if (massageDto.getPrice() != null) {
            existingMassage.setPrice(massageDto.getPrice());
        }
        if (massageDto.getDurationMinutes() != null) {
            existingMassage.setDurationMinutes(massageDto.getDurationMinutes());
        }

        Massage updatedMassage = massageRepository.save(existingMassage);
        return convertToDto(updatedMassage);
    }

    @Override
    @Transactional
    public void deleteMassage(Long massageId) {
        if (!massageRepository.existsById(massageId)) {
            throw new RuntimeException("Massage not found with id: " + massageId);
        }
        massageRepository.deleteById(massageId);
    }

    private MassageDto convertToDto(Massage massage) {
        return new MassageDto(
                massage.getId(),
                massage.getName(),
                massage.getDescription(),
                massage.getPrice(),
                massage.getDurationMinutes()
        );
    }

    private Massage convertToEntity(MassageDto massageDto) {
        return Massage.builder()
                .name(massageDto.getName())
                .description(massageDto.getDescription())
                .price(massageDto.getPrice() != null ? massageDto.getPrice() : 0.0)
                .durationMinutes(massageDto.getDurationMinutes() != null ? massageDto.getDurationMinutes() : 0)
                .build();
    }
}