package com.uc.ecommerce.controller.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {

    @NotEmpty(message = "Kullanıcı adı boş geçilemez.")
    @Size( max = 50,message = "Kullanıcı adı maximum 50 karakter olabilir.")
    private String username;
    @Setter
    @NotEmpty(message = "Şifre boş geçilemez.")
    @Size(min = 6, max = 32,message = "Şifrenizin uzunlu 6 ile 32 arasında olabilir." )
    private String password;
    @Setter
    private boolean rememberMe;

    public void setUsername(String username){
        this.username=username.replaceAll(" ","");
    }

}
