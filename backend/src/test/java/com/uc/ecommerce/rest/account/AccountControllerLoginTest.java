package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.RequestSpec;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.utils.TestAccountUtility;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;

public class AccountControllerLoginTest extends PlatformTestWithAuth {

    @Autowired
    private TestAccountUtility testAccountUtility;

    @BeforeEach
    public void before() {
        testAccountUtility.getTestAdmin();
    }

    @Test
    void testAdminLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");
        given().contentType(ContentType.JSON)
                .body(loginRequest).log().all()
                .when().post(path()).then().log().all().spec(ResponseSpec.isOkResponse());
    }

    private String path() {
        return String.format("/account/login");
    }


}
