package com.beyond.basic.b2_board.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 기본적으로 Entity는 상속이 불가능 하여 MappedSuperclass 붙여야 Entity와의 상속관계가 성립.
// MappedSuperclass는 getter가 적용이 안돼어서 따로 붙여줘야하는듯
@Getter
@MappedSuperclass
public class BaseTimeEntity {
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
