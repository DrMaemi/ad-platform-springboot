package com.ssg.backendpreassignment.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 엔티티의 생명주기를 감시하는 JpaAuditing 기능을 수행하는 엔티티 클래스
 * DB에 매핑되는 테이블 없이, 해당 엔티티를 상속하는 자식 엔티티에 대해 JpaAuditing 기능 수행
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {
    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable=false)
    private LocalDateTime lastModifiedDate;
}
