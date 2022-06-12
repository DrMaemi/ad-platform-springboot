package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.config.embeddable.AdChargeCalId;
import com.ssg.backendpreassignment.entity.AdChargeCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * AdChargeCalEntity의 영속성을 관리하며 엔티티에 매핑된 DB 테이블 'ADVERTISEMENT_CHARGE_CAL'에 CRUD 기능 수행
 * 복잡한 집계 쿼리를 처리하기 위한 Native Query 사용
 */
public interface AdChargeCalRepository extends JpaRepository<AdChargeCalEntity, AdChargeCalId> {
    @Query(value="SELECT AC.CLICKED_DATE, AC.BID_ID, C.ID AS COMPANY_ID, C.NAME AS COMPANY_NAME, P.ID AS PRODUCT_ID, P.PRODUCT_NAME, COUNT(*) AS CNT_CLICKED, COUNT(*)*AB.BID_PRICE AS TOTAL_CHARGE " +
            "FROM (SELECT ID, FORMATDATETIME(CLICKED_DATE, 'yyyy-MM-dd') AS CLICKED_DATE, BID_ID, BID_PRICE FROM ADVERTISEMENT_CHARGE WHERE FORMATDATETIME(CLICKED_DATE, 'yyyy-MM-dd')=DATEADD('DAY', -1, CURRENT_DATE)) AC, " +
            "ADVERTISEMENT_BID AB, COMPANY C, PRODUCT P " +
            "WHERE AC.BID_ID = AB.ID AND AB.COMPANY_ID = C.ID AND AB.PRODUCT_ID = P.ID " +
            "GROUP BY CLICKED_DATE, BID_ID"
            , nativeQuery=true)
    public List<AdChargeCalEntity> dailyCal();
}
