package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdBidDto;
import com.ssg.backendpreassignment.dto.AdBidReqDto;
import com.ssg.backendpreassignment.entity.AdBidEntity;
import com.ssg.backendpreassignment.entity.ContractEntity;
import com.ssg.backendpreassignment.entity.ProductEntity;
import com.ssg.backendpreassignment.repository.AdBidRepository;
import com.ssg.backendpreassignment.repository.ContractRepository;
import com.ssg.backendpreassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 광고입찰 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class AdBidService {
    private final AdBidRepository adBidRepository;
    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly=true)
    public AdBidDto getAdBid(Long id) {
        Optional<AdBidEntity> advertisementBidEntityWrapper = adBidRepository.findByIdJpqlFetch(id);
        AdBidEntity adBidEntity = advertisementBidEntityWrapper.get();
        return adBidEntity.toDto();
    }

    /**
     * 생성된 광고입찰 리스트 조회 요청에 대한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<AdBidDto> getAdBids() {
        List<AdBidEntity> advertisementBidEntities = adBidRepository.findAllJpqlFetch();
        return advertisementBidEntities.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }

    /**
     * 광고입찰 생성 요청에 대한 서비스 메서드
     * 요청 데이터를 받아 엔티티를 생성하여 영속성 컨텍스트에서 관리되도록 처리
     * 처리한 데이터 반환
     * @param adBidReqDto
     * @return AdBidDto
     */
    @Transactional
    public AdBidDto createAdBid(AdBidReqDto adBidReqDto) {
        ContractEntity contractEntity = contractRepository.findByCompanyIdJpqlFetch(adBidReqDto.getCompanyId()).get();
        ProductEntity productEntity = productRepository.findById(adBidReqDto.getProductId()).get();
        AdBidEntity adBidEntity = AdBidEntity.builder()
                .contractEntity(contractEntity)
                .productEntity(productEntity)
                .bidPrice(adBidReqDto.getBidPrice())
                .build();
        return adBidRepository.save(adBidEntity).toDto();
    }
}
