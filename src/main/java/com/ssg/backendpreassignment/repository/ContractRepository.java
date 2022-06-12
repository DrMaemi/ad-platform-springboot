package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * ContractEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'CONTRACT'에 CRUD 기능 수행
 * JOIN FETCH로 쿼리 성능 향상
 */
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    @Query("SELECT DISTINCT e FROM ContractEntity e JOIN FETCH e.companyEntity")
    List<ContractEntity> findAllJpqlFetch();

    @Query("SELECT e FROM ContractEntity e JOIN FETCH e.companyEntity WHERE e.companyEntity.id=:companyId")
    Optional<ContractEntity> findByCompanyIdJpqlFetch(@Param("companyId") Long companyId);
}
