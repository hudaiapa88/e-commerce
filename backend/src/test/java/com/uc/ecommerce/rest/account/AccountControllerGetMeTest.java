package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTest;
import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.utils.TestAdminUtility;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static io.restassured.RestAssured.given;

public class AccountControllerGetMeTest extends PlatformTestWithAuth {
    
    @Test
    void testAdminGetMe() {
        given().auth().oauth2(getAdmin().getToken()).contentType(ContentType.JSON)
                .log().all()
                .when().get(path()).then().log().all().spec(ResponseSpec.isOkResponse());
    }
    private String path() {
        return String.format("/account");
    }

}
