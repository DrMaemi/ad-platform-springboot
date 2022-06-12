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

/**
 * 업체 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
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

    /**
     * 계약 생성 시 업체 등록 유무를 검사하는 유효성 검사에 필요한 서비스 메서드
     * 업체 ID를 입력받아 해당 업체 엔티티를 조회했을 때 사업자번호 등록 유무에 따라 결과 반환
     * @param id
     * @return boolean
     */
    @Transactional(readOnly=true)
    public boolean isRegistered(Long id) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findById(id);

        if (companyEntityWrapper.isPresent()) {
            return companyEntityWrapper.get().getBusinessRegistrationNumber() != null;
        }

        return false;
    }

    /**
     * 상품 정보 리스트 셋팅 시 업체 Master 정보를 생성하거나 사전에 생성된 정보를 조회하기 위한 서비스 메서드
     * 상품 정보와 연관 관계를 셋팅하기 전에 필요한 비즈니스 로직 수행
     * @param companyName
     * @return CompanyDto
     */
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

    /**
     * 특정 업체 ID를 가지는 업체 정보와, 등록 요청한 상품 정보의 연관 관계를 셋팅하기 위한 서비스 메서드
     * @param id
     * @param productDto
     */
    @Transactional
    public void addProduct(Long id, ProductDto productDto) {
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findById(id);
        CompanyEntity companyEntity = companyEntityWrapper.get();
        ProductEntity productEntity = productDto.toEntityExceptCompany();
        productEntity.setCompanyEntity(companyEntity);
        productRepository.save(productEntity);
    }

    /**
     * 업체 Master 정보 리스트 조회 요청을 처리하기 위한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<CompanyDto> getCompanies() {
        List<CompanyEntity> companyEntities = companyRepository.findAllJpqlFetch();
        return companyEntities.stream().map(companyEntity -> companyEntity.toDto()).collect(Collectors.toList());
    }

    /**
     * 업체 정보 수정 요청을 처리하기 위한 서비스 메서드
     * @param companyReqDto
     * @return CompanyDto
     */
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
