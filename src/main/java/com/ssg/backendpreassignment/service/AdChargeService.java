package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.entity.AdBidEntity;
import com.ssg.backendpreassignment.entity.AdChargeEntity;
import com.ssg.backendpreassignment.repository.AdBidRepository;
import com.ssg.backendpreassignment.repository.AdChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdChargeService {
    private final AdChargeRepository adChargeRepository;
    private final AdBidRepository adBidRepository;

    @Transactional
    public void createChargeByBidId(Long bidId) {
        AdBidEntity adBidEntity = adBidRepository.findById(bidId).get();
        AdChargeEntity adChargeEntity = AdChargeEntity.builder()
                .adBidEntity(adBidEntity)
                .bidPrice(adBidEntity.getBidPrice())
                .build();
        adChargeRepository.save(adChargeEntity);
    }
}
