package com.uc.ecommerce.model.dto.account;

import com.uc.ecommerce.model.dto.base.TimestampBaseDto;
import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse extends TimestampBaseDto {
    private String firstName;
    private String lastName;
    private String username;
    private Phone phone;
    private String email;
    private Boolean isActive;
    private Role role;

}
