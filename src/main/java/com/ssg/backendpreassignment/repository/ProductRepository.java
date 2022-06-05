package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ContractEntity;
import com.ssg.backendpreassignment.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT e FROM ProductEntity e left join fetch e.companyEntity")
    List<ContractEntity> findAllJpqlFetch();
}
