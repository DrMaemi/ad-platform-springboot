package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.CompanyReqDto;
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

    @Transactional(readOnly=true)
    public CompanyDto findByNameExceptProducts(String companyName) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findByName(companyName);

        if (companyEntityWrapper.isPresent()) {
            return companyEntityWrapper.get().toDtoExceptProducts();
        }

        return null;
    }

    @Transactional(readOnly=true)
    public boolean isRegistered(Long id) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findById(id);

        if (companyEntityWrapper.isPresent()) {
            return companyEntityWrapper.get().getBusinessRegistrationNumber() != null;
        }

        return false;
    }

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
        ProductEntity productEntity = productDto.toEntityExceptCompany();
        productEntity.setCompanyEntity(companyEntity);
        productRepository.save(productEntity);
    }

    @Transactional(readOnly=true)
    public List<CompanyDto> getCompanies() {
        List<CompanyEntity> companyEntities = companyRepository.findAllJpqlFetch();
        return companyEntities.stream().map(companyEntity -> companyEntity.toDto()).collect(Collectors.toList());
    }

    @Transactional
    public CompanyDto updateCompany(CompanyReqDto companyReqDto) {
        CompanyEntity companyEntity = companyRepository.findByNameExceptProducts(companyReqDto.getCompanyName()).get();

        String businessRegNum = companyReqDto.getBusinessRegistrationNumber();
        String phoneNum = companyReqDto.getPhoneNumber();
        String addr = companyReqDto.getAddress();

        if (businessRegNum != null) companyEntity.setBusinessRegistrationNumber(businessRegNum);
        if (phoneNum != null) companyEntity.setPhoneNumber(phoneNum);
        if (addr != null) companyEntity.setAddress(addr);

        return companyEntity.toDtoExceptProducts();
    }
}
