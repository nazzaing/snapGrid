package com.snapgrid.common.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * 생성일, 수정일 공용 Entity
 * 생성자 수정자가 추가로 필요할 경우 BaseEntity를 extends 할것
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDateEntity{
    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate; // 생성일


    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate; // 수정일
}
