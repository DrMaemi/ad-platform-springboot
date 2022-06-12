package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdDisplayDto;
import com.ssg.backendpreassignment.entity.AdDisplayEntity;
import com.ssg.backendpreassignment.repository.AdDisplayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 광고전시 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class AdDisplayService {
    private final AdDisplayRepository adDisplayRepository;

    /**
     * 광고전시 리스트 조회 요청에 대한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<AdDisplayDto> getAds() {
        List<AdDisplayEntity> ads = adDisplayRepository.findAll();
        return ads.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }
}
