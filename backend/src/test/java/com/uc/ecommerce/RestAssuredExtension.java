package com.uc.ecommerce;

import io.restassured.RestAssured;
import jakarta.servlet.ServletConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import scala.sys.SystemProperties;

import java.util.Optional;
import java.util.Properties;


public class RestAssuredExtension implements BeforeAllCallback, AfterAllCallback {

    private static final int SERVER_PORT = 8088;
    private static final String BASE_URL = RestAssured.DEFAULT_URI + "/api/v1";


    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {

        RestAssured.port = SERVER_PORT;
        RestAssured.baseURI = BASE_URL;
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        RestAssured.reset();
    }

    private Optional<String> getSystemProperty(String propertyName) {
        return Optional.ofNullable(System.getProperty(propertyName));
    }

}
