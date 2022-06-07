package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.entity.AdvertisementBidEntity;
import com.ssg.backendpreassignment.entity.AdvertisementChargeEntity;
import com.ssg.backendpreassignment.repository.AdvertisementBidRepository;
import com.ssg.backendpreassignment.repository.AdvertisementChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvertisementChargeService {
    private final AdvertisementChargeRepository advertisementChargeRepository;
    private final AdvertisementBidRepository advertisementBidRepository;

    @Transactional
    public void createChargeByBidId(Long bidId) {
        AdvertisementBidEntity advertisementBidEntity = advertisementBidRepository.findById(bidId).get();
        AdvertisementChargeEntity advertisementChargeEntity = AdvertisementChargeEntity.builder()
                .advertisementBidEntity(advertisementBidEntity)
                .bidPrice(advertisementBidEntity.getBidPrice())
                .build();
        advertisementChargeRepository.save(advertisementChargeEntity);
    }
}
