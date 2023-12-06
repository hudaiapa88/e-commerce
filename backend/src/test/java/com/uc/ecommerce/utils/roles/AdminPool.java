package com.uc.ecommerce.utils.roles;

import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.account.Admin;

public class AdminPool {
    public static Admin getAdmin(){
        Admin admin= new Admin();
        admin.setFirstName("Admin");
        admin.setLastName("USERADMIN");
        admin.setUsername("admin");
        admin.setPassword("123456");
        Phone phone = new Phone();
        phone.setNumber("2001231212");
        phone.setAreaCode("+90");
        phone.setCountryCode("TR");
        admin.setPhone(phone);
        return admin;
    }
}
