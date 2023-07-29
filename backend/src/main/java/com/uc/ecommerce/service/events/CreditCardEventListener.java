package com.uc.ecommerce.service.events;

import com.uc.ecommerce.model.dto.card.CreateCreditCardRequest;
import com.uc.ecommerce.service.imp.CreditCardService;
import com.uc.ecommerce.service.imp.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditCardEventListener {
    private final CreditCardService creditCardService;
    private final UserService userService;
    @KafkaListener(topics = "card", groupId = "ecommerce")
    private void listener(CreateCreditCardRequest createCreditCardRequest){
        log.info(String.format("Json message received --> %s", createCreditCardRequest.toString()));

        creditCardService.save(createCreditCardRequest);
    }
}
