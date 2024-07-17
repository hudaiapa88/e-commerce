package com.uc.ecommerce.service.abstracts;


import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.model.entity.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountService {
    LoginResponse login(LoginRequest loginRequest);
    void disableAccount(Account account);

    Account findByUserName(String username);

    Boolean existByUsername(String s);

    void logout(HttpServletRequest request, HttpServletResponse response);

    Account findById(Long id);
    void active(Long id,String code);

    boolean existByEmail(String value);

}
