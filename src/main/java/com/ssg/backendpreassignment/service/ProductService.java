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

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly=true)
    public List<ProductDto> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> productEntity.toDto()).collect(Collectors.toList());
    }

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
