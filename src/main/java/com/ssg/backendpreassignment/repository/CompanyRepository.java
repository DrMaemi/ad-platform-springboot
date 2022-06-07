package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Query("SELECT DISTINCT e FROM CompanyEntity e WHERE e.name=:name")
    Optional<CompanyEntity> findByNameExceptProducts(@Param("name") String name);

    @Query("SELECT DISTINCT e FROM CompanyEntity e JOIN FETCH e.productEntities WHERE e.name=:name")
    Optional<CompanyEntity> findByNameJpqlFetch(@Param("name") String name);

    @Query("SELECT DISTINCT e FROM CompanyEntity e JOIN FETCH e.productEntities")
    List<CompanyEntity> findAllJpqlFetch();
}
