package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'PRODUCT'에 CRUD 기능 수행
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
