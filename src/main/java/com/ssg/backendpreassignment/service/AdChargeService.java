package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdChargeDto;
import com.ssg.backendpreassignment.entity.AdBidEntity;
import com.ssg.backendpreassignment.entity.AdChargeEntity;
import com.ssg.backendpreassignment.repository.AdBidRepository;
import com.ssg.backendpreassignment.repository.AdChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 광고과금 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class AdChargeService {
    private final AdChargeRepository adChargeRepository;
    private final AdBidRepository adBidRepository;

    /**
     * 광고과금 데이터 생성 요청에 대한 서비스 메서드
     * 광고입찰 ID에 따른 엔티티를 생성하여 영속성 컨텍스트에서 관리되도록 처리
     * @param bidId
     * @return AdChargeDto
     */
    @Transactional
    public AdChargeDto createAdCharge(Long bidId) {
        AdBidEntity adBidEntity = adBidRepository.findById(bidId).get();
        AdChargeEntity adChargeEntity = AdChargeEntity.builder()
                .adBidEntity(adBidEntity)
                .bidPrice(adBidEntity.getBidPrice())
                .build();
        return adChargeRepository.save(adChargeEntity).toDto();
    }
}
