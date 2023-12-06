package com.uc.ecommerce.controller.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {

    @NotEmpty(message = "{constraint.login.username.Empty.message}")
    @Size( max = 50,message = "{constraint.login.username.Size.message}")
    private String userName;
    @Setter
    @NotEmpty(message = "{constraint.login.password.Empty.message}")
    @Size(min = 6, max = 32,message = "{constraint.login.password.Size.message}" )
    private String password;
    @Setter
    private boolean rememberMe;

    public void setUsername(String username){
        this.userName=username.replaceAll(" ","");
    }

}
