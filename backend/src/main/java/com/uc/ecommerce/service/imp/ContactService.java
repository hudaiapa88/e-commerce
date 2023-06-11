package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.dto.contract.ContactRequest;

public interface ContactService {
    void send(ContactRequest contactRequest);
}
