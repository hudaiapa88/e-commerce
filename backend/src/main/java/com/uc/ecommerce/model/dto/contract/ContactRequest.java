package com.uc.ecommerce.model.dto.contract;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Size(max = 255,message = "{constraint.common.Size.message}")
    private String subject;
    @NotEmpty(message = "{constraint.common.Empty.message}")
    @Size(max = 255,message = "{constraint.common.Size.message}")
    private String message;

}
