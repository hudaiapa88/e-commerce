package com.uc.ecommerce.service.imp;


import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.model.entity.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountService {
    LoginResponse loginWithPassword(LoginRequest loginRequest);

    Account findByUserName(String username);

    Boolean existByUsername(String s);

    void logout(HttpServletRequest request, HttpServletResponse response);

    Account findById(Long id);
    void activeUser(Long userId);
}
