package com.uc.ecommerce.service;

import com.uc.ecommerce.core.sender.EmailDetails;
import com.uc.ecommerce.core.sender.EmailSender;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.service.imp.OrderEmailService;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailManager extends EmailManager implements OrderEmailService {
    public OrderEmailManager(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void sendEmailToUserForNewOrder(User user) {
        emailSender.send(EmailDetails.builder()
                .from(from)
                .to(user.getEmail())
                .subject("Siparişiniz Alındı")
                .text("Gönderiniz en kısa zamanda kargolanacaktır.")
                .build());
    }
}
