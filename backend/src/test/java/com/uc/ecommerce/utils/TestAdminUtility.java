package com.uc.ecommerce.utils;

import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.repository.AdminRepository;
import com.uc.ecommerce.utils.roles.AdminPool;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class TestAdminUtility {
    @Autowired
    private AdminRepository adminRepository;
    public Admin createAdmin() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = AdminPool.getAdmin();
        Long adminCount = adminRepository.count();
        if (adminCount == 0) {
            admin.setPassword(encoder.encode(admin.getPassword()));
            adminRepository.save(admin);
        }
        return admin;
    }

    public String getTestAdminToken() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(AdminPool.getAdmin().getUsername());
        loginRequest.setPassword(AdminPool.getAdmin().getPassword());
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
