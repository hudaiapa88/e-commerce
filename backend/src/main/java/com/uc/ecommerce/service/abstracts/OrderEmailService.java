package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.entity.account.User;

public interface OrderEmailService {
    void sendEmailToUserForNewOrder(User user);
}
