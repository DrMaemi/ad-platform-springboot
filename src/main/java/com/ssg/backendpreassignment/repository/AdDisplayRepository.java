package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdDisplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AdDisplayEntity 엔티티에 매핑된 DB 뷰(View) 'ADVERTISEMENT_DISPLAY'에 Read 기능 수행
 */
public interface AdDisplayRepository extends JpaRepository<AdDisplayEntity, Long> {
}
