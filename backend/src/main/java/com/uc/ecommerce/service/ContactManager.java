package com.uc.ecommerce.service;

import com.uc.ecommerce.core.security.SecurityContextUtil;
import com.uc.ecommerce.model.dto.contract.ContactRequest;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.service.abstracts.ContactEmailService;
import com.uc.ecommerce.service.abstracts.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactManager implements ContactService {
    private final ContactEmailService contactEmailService;
    private final SecurityContextUtil securityContextUtil;
    @Override
    public void send(ContactRequest contactRequest) {
        User user= securityContextUtil.getUser();
        contactEmailService.send(user,contactRequest);
    }
}
