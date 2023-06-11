package com.uc.ecommerce.model.dto.category;

import com.uc.ecommerce.model.dto.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse extends BaseDto {
    private String title;
    private CategoryResponse parent;
}
