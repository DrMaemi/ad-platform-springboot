package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.config.embeddable.AdChargeCalId;
import com.ssg.backendpreassignment.entity.AdChargeCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdChargeCalRepository extends JpaRepository<AdChargeCalEntity, AdChargeCalId> {

    @Query(value="SELECT AC.CLICKED_DATE, C.ID AS COMPANY_ID, C.NAME AS COMPANY_NAME, AB.ID AS BID_ID, P.ID AS PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_NAME, COUNT(*) AS CNT_CLICKED, COUNT(*)*AB.BID_PRICE AS TOTAL_CHARGE " +
            "FROM ADVERTISEMENT_CHARGE AC, " +
            "ADVERTISEMENT_BID AB, COMPANY C, PRODUCT P " +
            "WHERE AC.BID_ID = AB.ID AND AB.COMPANY_ID = C.ID AND AB.PRODUCT_ID = P.ID " +
            "GROUP BY CLICKED_DATE, COMPANY_ID"
            , nativeQuery=true)
    public List<AdChargeCalEntity> dailyCal();
}
