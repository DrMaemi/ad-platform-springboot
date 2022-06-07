package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.AdvertisementChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertisementChargeRepository extends JpaRepository<AdvertisementChargeEntity, Long> {
    @Query("SELECT e FROM AdvertisementChargeEntity e ")
    List<AdvertisementChargeEntity> findAllJpqlFetch();
}
