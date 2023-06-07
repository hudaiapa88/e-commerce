package com.uc.ecommerce.core.validator;

import com.uc.ecommerce.core.exception.validator.ValidatorException;
import com.uc.ecommerce.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class DuplicateEmailValidator implements Validator<String> {

   private final AccountRepository userRepository;


    public void validate(String email) throws ValidatorException {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateMailException("Bu Email Daha Önceden Alınmıştır.");
        }
    }
    public void validate(String email, AtomicInteger counter) throws ValidatorException {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateMailException(counter +". satırdaki email numarası daha önce alınmış.");
        }
    }

}
