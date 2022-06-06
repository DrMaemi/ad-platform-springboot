package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdvertisementDisplayDto;
import com.ssg.backendpreassignment.entity.AdvertisementDisplayEntity;
import com.ssg.backendpreassignment.repository.AdvertisementDisplayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertisementDisplayService {
    private final AdvertisementDisplayRepository advertisementDisplayRepository;

    public List<AdvertisementDisplayDto> getAds() {
        List<AdvertisementDisplayEntity> ads = advertisementDisplayRepository.findAll();
        return ads.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }
}
