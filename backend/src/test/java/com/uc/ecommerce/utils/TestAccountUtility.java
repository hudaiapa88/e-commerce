package com.uc.ecommerce.utils;

import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.repository.AdminRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TestAccountUtility {
    @Autowired
    private AdminRepository adminRepository;

    public Admin getTestAdmin() {
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
