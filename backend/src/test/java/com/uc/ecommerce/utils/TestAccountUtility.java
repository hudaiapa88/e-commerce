package com.uc.ecommerce.utils;

import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.repository.AdminRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component

public class TestAccountUtility {
    @Autowired
    AdminRepository adminRepository;

    public Admin createTestAdmin() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = new Admin();
        Long adminCount = adminRepository.count();
        if (adminCount == 0) {

            admin.setFirstName("Admin");
            admin.setLastName("USERADMIN");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("123456"));
            Phone phone = new Phone();
            phone.setNumber("2001231212");
            phone.setAreaCode("+90");
            phone.setCountryCode("TR");
            admin.setPhone(phone);
            adminRepository.save(admin);
        }
        return admin;
    }
}
