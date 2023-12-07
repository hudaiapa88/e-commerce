package com.uc.ecommerce.utils.account;

import com.uc.ecommerce.ResponseSpec;
import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.repository.UserRepository;
import com.uc.ecommerce.utils.account.roles.UserPool;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static io.restassured.RestAssured.given;
@Component
public class TestUserUtility {

    @Autowired
    private UserRepository userRepository;
    public User createUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByUsername(UserPool.getUser().getUsername());
        if (!user.isPresent()) {
            User u=UserPool.getUser();
            u.setPassword(encoder.encode(u.getPassword()));
            return userRepository.save(u);
        }
        return user.get();
    }

    public String getTestUserToken() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(UserPool.getUser().getUsername());
        loginRequest.setPassword(UserPool.getUser().getPassword());
        LoginResponse loginResponse = given().contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post(path()).then().spec(ResponseSpec.isOkResponse())
                .extract().body().as(LoginResponse.class);
        return loginResponse.getToken();
    }
    private String path() {
        return String.format("/account/login");
    }
}
