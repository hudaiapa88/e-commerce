package com.uc.ecommerce.core.validator;


import com.uc.ecommerce.core.exception.validator.DuplicateUsernameException;
import com.uc.ecommerce.core.exception.validator.ValidatorException;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class DuplicatePhoneValidator {

    private final AccountRepository accountRepository;

    public void validate(String areaCode,String number) throws ValidatorException {
        Optional<Account> user = accountRepository.findByPhone_AreaCodeAndPhone_Number(areaCode,number);
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Bu telefon numarası daha önce alınmış.");
        }
    }
    public void validate(String areaCode, String number, AtomicInteger counter) throws ValidatorException {
        Optional<Account> user = accountRepository.findByPhone_AreaCodeAndPhone_Number(areaCode,number);
        if (user.isPresent()) {
            throw new DuplicateUsernameException(counter +". satırdaki  telefon numarası daha önce alınmış.");
        }
    }
}
