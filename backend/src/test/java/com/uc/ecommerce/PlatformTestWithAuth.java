package com.uc.ecommerce;

import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.utils.TestAccountUtility;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class PlatformTestWithAuth extends PlatformTests{
    @Autowired
    private TestAccountUtility testAccountUtility;

    private Admin admin;

    @BeforeEach
    public void before_PlatformTestWithAuth(){
        admin = testAccountUtility.createTestAdmin();
    }

    public Admin getAdmin() {
        return admin;
    }
}
