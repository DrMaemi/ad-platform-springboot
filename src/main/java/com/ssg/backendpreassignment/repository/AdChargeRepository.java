package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AdChargeEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'ADVERTISEMENT_CHARGE'에 CRUD 기능 수행
 */
public interface AdChargeRepository extends JpaRepository<AdChargeEntity, Long> {
}
