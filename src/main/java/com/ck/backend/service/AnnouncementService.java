package com.ck.backend.service;

import com.ck.backend.dto.AnnouncementDto;
import com.ck.backend.dto.AnnouncementRequestDto;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementDto> getAllAnnouncements();
    AnnouncementDto getAnnouncementById(Long announcementId);
    AnnouncementDto createAnnouncement(AnnouncementRequestDto requestDto);
    AnnouncementDto updateAnnouncement(Long announcementId, AnnouncementRequestDto requestDto);
    void deleteAnnouncement(Long announcementId);
}
