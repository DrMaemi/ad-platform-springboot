package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdBidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * AdBidEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'ADVERTISEMENT_BID'에 CRUD 기능 수행
 * JOIN FETCH로 쿼리 성능 향상
 */
public interface AdBidRepository extends JpaRepository<AdBidEntity, Long> {
    @Query("SELECT DISTINCT e" +
            " FROM AdBidEntity e" +
            " JOIN FETCH e.contractEntity JOIN FETCH e.contractEntity.companyEntity JOIN FETCH e.productEntity")
    List<AdBidEntity> findAllJpqlFetch();

    @Query("SELECT DISTINCT e" +
            " FROM AdBidEntity e" +
            " JOIN FETCH e.contractEntity JOIN FETCH e.contractEntity.companyEntity JOIN FETCH e.productEntity" +
            " WHERE e.id=:id")
    Optional<AdBidEntity> findByIdJpqlFetch(@Param("id") Long id);
}
