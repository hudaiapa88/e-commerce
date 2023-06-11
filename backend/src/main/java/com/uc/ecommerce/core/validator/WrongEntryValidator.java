package com.uc.ecommerce.core.validator;

import com.uc.ecommerce.core.exception.validator.ValidatorException;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.model.enums.AccountLogType;
import com.uc.ecommerce.service.imp.AccountLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WrongEntryValidator {
    private final AccountLogService accountLogService;

    public Boolean validate(Account account)  {
        return accountLogService.findByAccount_IdAndAccountLogType(account.getId(), AccountLogType.WRONG_ENTRY).size()==5?Boolean.TRUE:Boolean.FALSE;
    }
}
