package com.uc.ecommerce.controller.contact;

import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.model.dto.contract.ContactRequest;
import com.uc.ecommerce.service.imp.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contract")
@RequiredArgsConstructor
@IsAuthenticated
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public void send( @Valid @RequestBody ContactRequest contactRequest){
        contactService.send(contactRequest);
    }
}
