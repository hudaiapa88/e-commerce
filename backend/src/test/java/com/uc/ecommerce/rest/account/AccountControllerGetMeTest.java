package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.MeResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import static io.restassured.RestAssured.given;

public class AccountControllerGetMeTest extends PlatformTestWithAuth {

    @Test
    void testAdminGetMe() {
      MeResponse meResponse= given().auth().oauth2(getAdmin().getToken()).contentType(ContentType.JSON)
                .log().all()
                .when().get(path()).then().log().all().spec(ResponseSpec.isOkResponse()).extract().body().as(MeResponse.class);
        assertThat(meResponse.getId()).isEqualTo(getAdmin().getAccount().getId());
        assertThat(meResponse.getFirstName()).isEqualTo(getAdmin().getAccount().getFirstName());
        assertThat(meResponse.getLastName()).isEqualTo(getAdmin().getAccount().getLastName());
        assertThat(meResponse.getUserName()).isEqualTo(getAdmin().getAccount().getUsername());
        assertThat(meResponse.getEmail()).isEqualTo(getAdmin().getAccount().getEmail());
        assertThat(meResponse.getRole()).isEqualTo(getAdmin().getAccount().getRole());
    }
    private String path() {
        return String.format("/account");
    }

}
