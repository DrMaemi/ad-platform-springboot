package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * CompanyEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'COMPANY'에 CRUD 기능 수행
 * JOIN FETCH로 쿼리 성능 향상
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByName(String name);
    boolean existsByBusinessRegistrationNumber(String businessRegistrationNumber);
    Optional<CompanyEntity> findByName(String name);

    @Query("SELECT DISTINCT e FROM CompanyEntity e WHERE e.name=:name")
    Optional<CompanyEntity> findByNameExceptProducts(@Param("name") String name);

    @Query("SELECT DISTINCT e FROM CompanyEntity e JOIN FETCH e.productEntities WHERE e.name=:name")
    Optional<CompanyEntity> findByNameJpqlFetch(@Param("name") String name);

    @Query("SELECT DISTINCT e FROM CompanyEntity e JOIN FETCH e.productEntities")
    List<CompanyEntity> findAllJpqlFetch();
}
