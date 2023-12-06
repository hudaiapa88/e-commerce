package com.uc.ecommerce.utils.roles;

import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.model.entity.account.User;


public class UserPool {
    public static User getUser(){
        User user= new User();
        user.setFirstName("User123");
        user.setLastName("LASUSER");
        user.setUsername("username123");
        user.setPassword("123456");
        Phone phone = new Phone();
        phone.setNumber("2001231212");
        phone.setAreaCode("+90");
        phone.setCountryCode("TR");
        user.setPhone(phone);
        return user;
    }
}
