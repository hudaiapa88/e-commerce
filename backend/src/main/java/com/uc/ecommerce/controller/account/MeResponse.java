package com.uc.ecommerce.controller.account;

import com.uc.ecommerce.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;


}
