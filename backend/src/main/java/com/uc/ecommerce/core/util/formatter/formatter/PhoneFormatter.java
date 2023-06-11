package com.uc.ecommerce.core.util.formatter.formatter;

import org.apache.commons.lang3.RegExUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneFormatter  {

    public static String format(String phone){

        return RegExUtils.replacePattern(phone,"[^0-9+\\+]","");
    }

}
