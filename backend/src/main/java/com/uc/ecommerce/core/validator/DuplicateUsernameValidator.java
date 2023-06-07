package com.uc.ecommerce.core.validator;


import com.uc.ecommerce.core.exception.validator.DuplicateUsernameException;
import com.uc.ecommerce.core.exception.validator.ValidatorException;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DuplicateUsernameValidator implements Validator<String>  {

    private final AccountRepository accountRepository;

    @Override
    public void validate(String username) throws ValidatorException {
        Optional<Account> user = accountRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new DuplicateUsernameException("Bu kullanıcı adı daha önce alınmış.");
        }
    }



}
