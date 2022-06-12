package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.entity.ProductEntity;
import com.ssg.backendpreassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 상품 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * 등록된 모든 상품 정보 리스트 조회 요청을 처리하기 위한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<ProductDto> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> productEntity.toDto()).collect(Collectors.toList());
    }

    /**
     * 상품 ID에 따른 상품 정보 조회 요청을 처리하기 위한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public ProductDto getProduct(Long id) {
        Optional<ProductEntity> productEntityWrapper = productRepository.findById(id);
        ProductEntity productEntity = productEntityWrapper.get();
        return productEntity.toDto();
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
