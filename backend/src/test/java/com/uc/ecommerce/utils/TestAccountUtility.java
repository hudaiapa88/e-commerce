package com.uc.ecommerce.utils;

import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.AccountController;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.repository.AdminRepository;
import io.restassured.http.ContentType;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@Component
public class TestAccountUtility {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountController accountController;

    public Admin createAdmin() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = new Admin();
        Long adminCount = adminRepository.count();
        if (adminCount == 0) {

            admin.setFirstName("Admin");
            admin.setLastName("USERADMIN");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("123456"));
            Phone phone = new Phone();
            phone.setNumber("2001231212");
            phone.setAreaCode("+90");
            phone.setCountryCode("TR");
            admin.setPhone(phone);
            adminRepository.save(admin);
        }
        return admin;
    }

    public String getTestAdminToken() {
        createAdmin();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");
        LoginResponse loginResponse = given().contentType(ContentType.JSON)
                .body(loginRequest).log().all()
                .when().post(path()).then().log().all().spec(ResponseSpec.isOkResponse())
                .extract().body().as(LoginResponse.class);
        return loginResponse.getToken();
    }

    private String path() {
        return String.format("/account/login");
    }
}
