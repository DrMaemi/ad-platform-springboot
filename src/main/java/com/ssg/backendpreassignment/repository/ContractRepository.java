package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    @Query("SELECT DISTINCT e FROM ContractEntity e JOIN FETCH e.companyEntity")
    List<ContractEntity> findAllJpqlFetch();

    @Query("SELECT e FROM ContractEntity e JOIN FETCH e.companyEntity WHERE e.companyEntity.id=:companyId")
    Optional<ContractEntity> findByCompanyIdJpqlFetch(@Param("companyId") Long companyId);
}
