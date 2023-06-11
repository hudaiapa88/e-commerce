package com.uc.ecommerce.service;

import com.uc.ecommerce.core.sender.EmailDetails;
import com.uc.ecommerce.core.sender.EmailSender;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.service.imp.AccountEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountEmailManager extends EmailManager implements AccountEmailService {

    public AccountEmailManager(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void sendEmailToAdminForNewUser(User user) {
        emailSender.send(EmailDetails.builder()
                        .from(from)
                        .to(user.getEmail())
                        .subject("Üyelik Başvurusu")
                        .text("üyeliğinizi tamalamak için linke tıklayın.    "+
                                baseUrl+"/account/active/"+user.getId()+"?code="+user.getVerificationCode()

                        )
                .build());
    }
}
