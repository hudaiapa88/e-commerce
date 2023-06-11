package com.uc.ecommerce.controller.account;

import com.uc.ecommerce.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String userName;
    private String token;
    private Role role;

}