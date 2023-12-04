package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.RequestSpec;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.utils.TestAccountUtility;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerLoginTest extends PlatformTestWithAuth {

    @Autowired
    TestAccountUtility testAccountUtility;

    @BeforeEach
    public void before(){
        testAccountUtility.createTestAdmin();
    }

    @Test
    void testAdminLogin(){
        LoginRequest loginRequest= new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");
        RequestSpec.given().jsonRequest().body(loginRequest)
                .when().post(path())
                .then()
                .spec(ResponseSpec.isOkResponse());
    }
    private String path() {
        return String.format("/account/login");
    }







}
