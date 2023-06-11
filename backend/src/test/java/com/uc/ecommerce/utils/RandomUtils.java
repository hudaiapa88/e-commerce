package com.uc.ecommerce.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomUtils {
    public String randomPhoneNumber(){
        return "+9054"+ RandomStringUtils.randomNumeric(8);
    }
    public String randomFirstname(){
        return"Ali"+ RandomStringUtils.randomAlphabetic(5);
    }
    public String randomLastname(){
        return"Bakır"+ RandomStringUtils.randomAlphabetic(5);
    }
}
