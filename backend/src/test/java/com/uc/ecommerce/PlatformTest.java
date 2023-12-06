package com.uc.ecommerce;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")

@ExtendWith(RestAssuredExtension.class)
public class PlatformTest {

    @Test
    void contextLoads() {
      /*  RestAssured.port = port.orElse(RestAssured.DEFAULT_PORT);
        RestAssured.baseURI = baseUri.orElse(RestAssured.DEFAULT_URI);
        RestAssured.rootPath = rootPath.orElse(RestAssured.DEFAULT_PATH);*/
    }
}
