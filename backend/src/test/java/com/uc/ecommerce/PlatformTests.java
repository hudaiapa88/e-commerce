package com.uc.ecommerce;


import io.restassured.RestAssured;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")

@ExtendWith(RestAssuredExtension.class)
public class PlatformTests {

    @Test
    void contextLoads() {
      /*  RestAssured.port = port.orElse(RestAssured.DEFAULT_PORT);
        RestAssured.baseURI = baseUri.orElse(RestAssured.DEFAULT_URI);
        RestAssured.rootPath = rootPath.orElse(RestAssured.DEFAULT_PATH);*/
    }
}
