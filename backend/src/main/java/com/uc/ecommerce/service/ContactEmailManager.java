package com.uc.ecommerce.service;

import com.uc.ecommerce.core.sender.EmailDetails;
import com.uc.ecommerce.core.sender.EmailSender;
import com.uc.ecommerce.model.dto.contract.ContactRequest;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.service.abstracts.ContactEmailService;
import org.springframework.stereotype.Service;

@Service
public class ContactEmailManager extends EmailManager implements ContactEmailService {

    public ContactEmailManager(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void send(User user, ContactRequest contactRequest) {
        emailSender.send(EmailDetails.builder()
                .from(from)
                .to(to)
                .subject("İLETİŞİM")
                .text(contactRequest.getMessage())
                .build());
    }
}
