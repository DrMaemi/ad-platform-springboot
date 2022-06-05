package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.entity.CompanyEntity;
import com.ssg.backendpreassignment.entity.ProductEntity;
import com.ssg.backendpreassignment.repository.CompanyRepository;
import com.ssg.backendpreassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDto findOrCreateByCompanyName(String companyName) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findByNameExceptProducts(companyName);
        // 만약 과거에 등록한 상품 정보에 있던 업체 명이라면 해당 업체에 대한 정보 반환
        if (companyEntityWrapper.isPresent()) {
            return companyEntityWrapper.get().toDto();
        }
        // 그렇지 않다면 새로운 업체이므로 엔티티 생성 후 저장
        return companyRepository.save(CompanyEntity.builder()
                        .name(companyName)
                        .productEntities(new ArrayList<>())
                        .build()).toDto();
    }

    @Transactional
    public void addProduct(Long id, ProductDto productDto) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findById(id);
        CompanyEntity companyEntity = companyEntityWrapper.get();
        ProductEntity productEntity = productDto.toEntity();
        productEntity.setCompanyEntity(companyEntity);
        productRepository.save(productEntity);
    }

    @Transactional(readOnly=true)
    public List<CompanyDto> getCompanies() {
        List<CompanyEntity> companyEntities = companyRepository.findAllJpqlFetch();
        return companyEntities.stream().map(companyEntity -> companyEntity.toDto()).collect(Collectors.toList());
    }
}
