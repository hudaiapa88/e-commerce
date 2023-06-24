package com.uc.ecommerce.service.events;

import com.uc.ecommerce.model.dto.card.SaveCreditCardRequest;
import com.uc.ecommerce.model.entity.card.CreditCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditCardProducer {
    private final KafkaTemplate<String, SaveCreditCardRequest> kafkaTemplate;
    public void publish(SaveCreditCardRequest data){
        log.info(String.format("Message sent -> %s", data.toString()));
        Message<SaveCreditCardRequest> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,"card")
                .build();

        kafkaTemplate.send(message);
    }
}
