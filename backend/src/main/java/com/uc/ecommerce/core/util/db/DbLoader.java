package com.uc.ecommerce.core.util.db;


import com.uc.ecommerce.model.embedded.Phone;

import com.uc.ecommerce.model.entity.account.Admin;

import com.uc.ecommerce.repository.AccountRepository;
import com.uc.ecommerce.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DbLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long adminCount = adminRepository.count();
        if (adminCount == 0) {
            Admin admin = new Admin();
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

    }
}
