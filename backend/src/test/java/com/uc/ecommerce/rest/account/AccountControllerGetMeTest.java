package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.utils.TestAccountUtility;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class AccountControllerGetMeTest extends PlatformTestWithAuth {
    @Autowired
    private TestAccountUtility testAccountUtility;

    @BeforeEach
    public void before() {
    }

    @Test
    void testAdminGetMe() {


        given().auth().oauth2(testAccountUtility.getTestAdminToken()).contentType(ContentType.JSON)
                .log().all()
                .when().get(path()).then().log().all().spec(ResponseSpec.isOkResponse());
    }
    private String path() {
        return String.format("/account");
    }

}
