package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
