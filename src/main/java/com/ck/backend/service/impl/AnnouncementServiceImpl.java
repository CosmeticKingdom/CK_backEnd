package com.ck.backend.service.impl;

import com.ck.backend.dto.AnnouncementDto;
import com.ck.backend.dto.AnnouncementRequestDto;
import com.ck.backend.entity.Announcement;
import com.ck.backend.repository.AnnouncementRepository;
import com.ck.backend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto getAnnouncementById(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + announcementId));
        return convertToDto(announcement);
    }

    @Override
    @Transactional
    public AnnouncementDto createAnnouncement(AnnouncementRequestDto requestDto) {
        Announcement announcement = new Announcement();
        announcement.setTitle(requestDto.getTitle());
        announcement.setContent(requestDto.getContent());
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return convertToDto(savedAnnouncement);
    }

    @Override
    @Transactional
    public AnnouncementDto updateAnnouncement(Long announcementId, AnnouncementRequestDto requestDto) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + announcementId));
        announcement.setTitle(requestDto.getTitle());
        announcement.setContent(requestDto.getContent());
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return convertToDto(updatedAnnouncement);
    }

    @Override
    @Transactional
    public void deleteAnnouncement(Long announcementId) {
        if (!announcementRepository.existsById(announcementId)) {
            throw new RuntimeException("Announcement not found with id: " + announcementId);
        }
        announcementRepository.deleteById(announcementId);
    }

    private AnnouncementDto convertToDto(Announcement announcement) {
        return new AnnouncementDto(announcement.getId(), announcement.getTitle(), announcement.getContent(), announcement.getCreatedAt());
    }
}
