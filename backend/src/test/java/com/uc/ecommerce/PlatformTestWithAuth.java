package com.uc.ecommerce;

import com.uc.ecommerce.utils.account.TestAdminUtility;
import com.uc.ecommerce.utils.account.TestUserUtility;
import com.uc.ecommerce.utils.account.roles.TestAdmin;
import com.uc.ecommerce.utils.account.roles.TestUser;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class PlatformTestWithAuth extends PlatformTest {
    @Autowired
    private TestUserUtility testUserUtility;
    @Autowired
    TestAdminUtility testAdminUtility;
    @Getter
    private TestAdmin admin= new TestAdmin();
    @Getter
    private TestUser user= new TestUser();
    @BeforeEach
    public void before_PlatformTestWithAuthentification() {
        admin.setAccount(testAdminUtility.createAdmin());
        admin.setToken(testAdminUtility.getTestAdminToken());
        user.setAccount(testUserUtility.createUser());
        user.setToken(testAdminUtility.getTestAdminToken());
    }


}
