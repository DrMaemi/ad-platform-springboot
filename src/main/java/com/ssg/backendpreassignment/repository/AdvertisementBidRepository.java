package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdvertisementBidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdvertisementBidRepository extends JpaRepository<AdvertisementBidEntity, Long> {
    @Query("SELECT DISTINCT e" +
            " FROM AdvertisementBidEntity e" +
            " JOIN FETCH e.contractEntity JOIN FETCH e.contractEntity.companyEntity JOIN FETCH e.productEntity")
    List<AdvertisementBidEntity> findAllJpqlFetch();

    @Query("SELECT e" +
            " FROM AdvertisementBidEntity e" +
            " JOIN FETCH e.contractEntity JOIN FETCH e.contractEntity.companyEntity JOIN FETCH e.productEntity" +
            " WHERE e.id=:id")
    Optional<AdvertisementBidEntity> findByIdJpqlFetch(@Param("id") Long id);
}
