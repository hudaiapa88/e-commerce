package com.uc.ecommerce.service.events;

import com.uc.ecommerce.model.dto.card.SaveCreditCardRequest;
import com.uc.ecommerce.model.entity.card.CreditCard;
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
    private void listener(SaveCreditCardRequest saveCreditCardRequest){
        log.info(String.format("Json message received --> %s", saveCreditCardRequest.toString()));

        creditCardService.save(saveCreditCardRequest);
    }
}
