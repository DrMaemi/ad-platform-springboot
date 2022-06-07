package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdvertisementBidDto;
import com.ssg.backendpreassignment.entity.AdvertisementBidEntity;
import com.ssg.backendpreassignment.entity.ContractEntity;
import com.ssg.backendpreassignment.entity.ProductEntity;
import com.ssg.backendpreassignment.repository.AdvertisementBidRepository;
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
public class AdvertisementBidService {
    private final AdvertisementBidRepository advertisementBidRepository;
    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly=true)
    public AdvertisementBidDto getBid(Long id) {
        Optional<AdvertisementBidEntity> advertisementBidEntityWrapper = advertisementBidRepository.findByIdJpqlFetch(id);
        AdvertisementBidEntity advertisementBidEntity = advertisementBidEntityWrapper.get();
        return advertisementBidEntity.toDto();
    }

    @Transactional(readOnly=true)
    public List<AdvertisementBidDto> getBids() {
        List<AdvertisementBidEntity> advertisementBidEntities = advertisementBidRepository.findAllJpqlFetch();
        return advertisementBidEntities.stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }

    @Transactional
    public void registerBid(AdvertisementBidDto advertisementBidDto) {
        ContractEntity contractEntity = contractRepository.findByCompanyId(advertisementBidDto.getContractDto().getCompanyDto().getId()).get();
        ProductEntity productEntity = productRepository.findById(advertisementBidDto.getProductDto().getId()).get();
        AdvertisementBidEntity advertisementBidEntity = advertisementBidDto.toEntity();
        advertisementBidEntity.setContractEntity(contractEntity);
        advertisementBidEntity.setProductEntity(productEntity);
        advertisementBidRepository.save(advertisementBidEntity);
    }
}
