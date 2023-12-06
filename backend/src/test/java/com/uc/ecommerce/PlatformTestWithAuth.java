package com.uc.ecommerce;

import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.utils.TestAccountUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class PlatformTestWithAuth extends PlatformTests{

    @Autowired
    private TestAccountUtility testAccountUtility ;

    private Admin admin;

    @BeforeEach
    public void before_PlatformTestWithAuth(){
        admin = testAccountUtility.createAdmin();
    }

    public Admin getAdmin() {
        return admin;
    }
}
