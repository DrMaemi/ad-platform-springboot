package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    @Query("SELECT e FROM ContractEntity e left join fetch e.companyEntity")
    public List<ContractEntity> findAllJpqlFetch();
}
