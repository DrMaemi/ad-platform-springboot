package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdBidDto;
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

@Service
@RequiredArgsConstructor
public class AdBidService {
    private final AdBidRepository adBidRepository;
    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly=true)
    public AdBidDto getBid(Long id) {
        Optional<AdBidEntity> advertisementBidEntityWrapper = adBidRepository.findByIdJpqlFetch(id);
        AdBidEntity adBidEntity = advertisementBidEntityWrapper.get();
        return adBidEntity.toDto();
    }

    @Transactional(readOnly=true)
    public List<AdBidDto> getBids() {
        List<AdBidEntity> advertisementBidEntities = adBidRepository.findAllJpqlFetch();
        return advertisementBidEntities.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }

    @Transactional
    public void registerBid(AdBidDto adBidDto) {
        ContractEntity contractEntity = contractRepository.findByCompanyIdJpqlFetch(adBidDto.getContractDto().getCompanyDto().getId()).get();
        ProductEntity productEntity = productRepository.findById(adBidDto.getProductDto().getId()).get();
        AdBidEntity adBidEntity = adBidDto.toEntity();
        adBidEntity.setContractEntity(contractEntity);
        adBidEntity.setProductEntity(productEntity);
        adBidRepository.save(adBidEntity);
    }
}
