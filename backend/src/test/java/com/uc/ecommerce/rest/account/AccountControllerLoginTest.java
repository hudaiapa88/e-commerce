package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.utils.account.roles.AdminPool;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class AccountControllerLoginTest extends PlatformTestWithAuth {

    @Test
    void testAdminLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(AdminPool.getAdmin().getUsername());
        loginRequest.setPassword(AdminPool.getAdmin().getPassword());
        given().contentType(ContentType.JSON)
                .body(loginRequest).log().all()
                .when().post(path()).then().log().all().spec(ResponseSpec.isOkResponse());
    }

    private String path() {
        return String.format("/account/login");
    }


}
