package com.snapgrid.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 생성자, 수정자 공용 Entity
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseDateEntity{


    @CreatedBy
    @Column(name = "creator_id", updatable = false)
    private Long creatorId; // 생성자 ID


    @LastModifiedBy
    @Column(name = "updater_id")
    private Long updaterId; // 수정자 ID

}
