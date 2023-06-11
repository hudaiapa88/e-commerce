package com.uc.ecommerce.model.dto.account;

import com.uc.ecommerce.model.dto.base.TimestampBaseDto;
import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.embedded.Phone;
import lombok.Data;

@Data
public class UserResponse extends TimestampBaseDto {
    private Address address;
    private String firstName;
    private String lastName;
    private String username;
    private Phone phone;
    private String email;
    private Boolean isActive;
    private String title;
}
