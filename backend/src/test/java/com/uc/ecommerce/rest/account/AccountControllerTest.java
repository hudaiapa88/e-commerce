package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.PlatformTestWithAuth;
import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.MeResponse;
import com.uc.ecommerce.utils.account.roles.AdminPool;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("account")
public class AccountControllerTest extends PlatformTestWithAuth{

    @Nested
    @DisplayName("login")
    class Login{
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

    @Nested
    @DisplayName("get me")
    class GetMe{
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
}
