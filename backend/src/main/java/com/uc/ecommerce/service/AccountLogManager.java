package com.uc.ecommerce.service;

import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.entity.log.AccountLog;
import com.uc.ecommerce.model.enums.AccountLogType;
import com.uc.ecommerce.repository.log.AccountLogRepository;
import com.uc.ecommerce.service.imp.AccountLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountLogManager implements AccountLogService {
    private final AccountLogRepository accountLogRepository;
    @Transactional
    @Override
    public void save(Account account, String title, AccountLogType accountLogType) {
        accountLogRepository.save(AccountLog.create(account,title,accountLogType));
    }

    @Override
    public List<AccountLog> findByAccount_IdAndAccountLogType(Long userId, AccountLogType accountLogType) {
        return accountLogRepository.findByAccount_IdAndAccountLogType(userId,accountLogType);
    }

    @Override
    public void deleteByAccount_IdAndAccountLogType(Long id, AccountLogType accountLogType) {
         accountLogRepository.deleteByAccount_IdAndAccountLogType( id,  accountLogType);
    }
}
