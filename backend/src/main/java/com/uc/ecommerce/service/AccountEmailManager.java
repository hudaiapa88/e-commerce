package com.uc.ecommerce.service;

import com.uc.ecommerce.core.sender.EmailDetails;
import com.uc.ecommerce.core.sender.EmailSender;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.service.imp.AccountEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEmailManager implements AccountEmailService {
    private final EmailSender emailSender;
    @Value("${ecommerce.mail.to.appEmail}")
    private String to;
    @Value("${ecommerce.mail.from.appEmail}")
    private String from;
    @Value("${app.base.url}")
    private String baseUrl;
    @Override
    public void sendEmailToAdminForNewUser(User user) {
        emailSender.send(EmailDetails.builder()
                        .from(from)
                        .to(to)
                        .subject("Üyelik Başvurusu")
                        .text(baseUrl+"/user/active/"+user.getId())
                .build());
    }
}
