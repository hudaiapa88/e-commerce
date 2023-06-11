package com.uc.ecommerce.rest.account;

import com.uc.ecommerce.RequestSpec;
import com.uc.ecommerce.ResponseSpec;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AccountControllerLoginTest {


    private String path(String id) {
        return String.format("/account/login/%s", id);
    }
}
