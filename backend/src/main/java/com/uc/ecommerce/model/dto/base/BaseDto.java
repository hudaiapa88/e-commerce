package com.uc.ecommerce.model.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

    private Long id;

}
