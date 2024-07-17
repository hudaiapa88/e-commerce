package com.uc.ecommerce.service;

import com.uc.ecommerce.core.sender.EmailSender;
import com.uc.ecommerce.service.abstracts.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailManager implements EmailService {

    protected final EmailSender emailSender;

    @Value("${ecommerce.mail.to.appEmail}")
    protected String to;
    @Value("${ecommerce.mail.from.appEmail}")
    protected String from;
    @Value("${app.base.url}")
    protected String baseUrl;

}
