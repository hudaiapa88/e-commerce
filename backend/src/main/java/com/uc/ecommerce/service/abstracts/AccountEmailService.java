package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.entity.account.User;

public interface AccountEmailService {
    void sendEmailToAdminForNewUser(User user);
}
