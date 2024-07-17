package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.dto.contract.ContactRequest;

public interface ContactService {
    void send(ContactRequest contactRequest);
}
