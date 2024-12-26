package org.study.hhplusclean.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 이 클래스를 상속하면 멤버 변수가 있을 텐데 그것을 컬럼으로 인식해줘
@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
public abstract class Timestamped {

    // 생성일자
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정일자
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
