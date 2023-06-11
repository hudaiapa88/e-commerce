package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.dto.contract.ContactRequest;
import com.uc.ecommerce.model.entity.account.User;

public interface ContactEmailService {

    void send(User user, ContactRequest contactRequest);
}
