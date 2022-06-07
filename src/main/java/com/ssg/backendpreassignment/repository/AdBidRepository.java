package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdBidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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
