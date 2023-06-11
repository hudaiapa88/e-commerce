package com.uc.ecommerce.model.entity.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Getter
@ToString
public abstract class AbstractTimestampEntity extends BaseEntity {

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @PrePersist
    protected void onCreate() {
        updatedDateTime = createdDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

}
