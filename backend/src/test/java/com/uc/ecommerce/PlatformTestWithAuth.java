package com.uc.ecommerce;

import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.utils.TestAdminUtility;
import com.uc.ecommerce.utils.TestUserUtility;
import com.uc.ecommerce.utils.roles.TestAdmin;
import com.uc.ecommerce.utils.roles.TestUser;
import lombok.Getter;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class PlatformTestWithAuth extends PlatformTest {
    @Autowired
    private TestUserUtility testUserUtility;
    @Autowired
    TestAdminUtility testAdminUtility;
    @Getter
    private TestAdmin admin;
    @Getter
    private TestUser user;
    @BeforeEach
    public void before_PlatformTestWithAuthentification() {
        admin.setAccount(testAdminUtility.createAdmin());
        admin.setToken(testAdminUtility.getTestAdminToken());
        user.setAccount(testUserUtility.createUser());
        user.setToken(testAdminUtility.getTestAdminToken());
    }


}
