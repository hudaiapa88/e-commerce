package com.uc.ecommerce.model.entity.log;

import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import com.uc.ecommerce.model.enums.LogType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "logType", discriminatorType = DiscriminatorType.STRING)
public class Log extends AbstractTimestampEntity {
    private String title;
    @Column(insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private LogType logType;
}
