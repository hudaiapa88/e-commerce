package com.uc.ecommerce.service;


import com.uc.ecommerce.core.security.CustomAccountDetails;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Component
public class UserDetailsManager implements UserDetailsService {

    @Autowired
     AccountRepository accountRepository;

    @Override
    public CustomAccountDetails loadUserByUsername(String username)  {
        //List<User> all = userRepository.findAll();
        accountRepository.findByFullPhone(username);
        Optional<Account> optionalUser = accountRepository.findByUsername(username);
        Account account = optionalUser.orElseThrow(() -> new UsernameNotFoundException(""));
        return new CustomAccountDetails(account);
    }

    public boolean userExists(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }
}

