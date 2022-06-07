package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdDisplayDto;
import com.ssg.backendpreassignment.entity.AdDisplayEntity;
import com.ssg.backendpreassignment.repository.AdDisplayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdDisplayService {
    private final AdDisplayRepository adDisplayRepository;

    @Transactional(readOnly=true)
    public List<AdDisplayDto> getAds() {
        List<AdDisplayEntity> ads = adDisplayRepository.findAll();
        return ads.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }
}
