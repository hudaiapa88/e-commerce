package com.uc.ecommerce.service;


import com.uc.ecommerce.controller.account.LoginRequest;
import com.uc.ecommerce.controller.account.LoginResponse;
import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.exception.IncorrectEntryException;
import com.uc.ecommerce.core.exception.validator.UserDisabledException;
import com.uc.ecommerce.core.security.CustomAccountDetails;
import com.uc.ecommerce.core.security.JwtTokenUtil;
import com.uc.ecommerce.core.security.SecurityContextUtil;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.repository.AccountRepository;
import com.uc.ecommerce.service.imp.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountRepository accountRepository;
    private final SecurityContextUtil securityContextUtil;

    @Override
    public LoginResponse loginWithPassword(LoginRequest customerLoginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerLoginRequest.getUsername(), customerLoginRequest.getPassword()));
            CustomAccountDetails customUserDetails = (CustomAccountDetails) authentication.getPrincipal();
            Account currentUser = customUserDetails.getAccount();
            final String token = jwtTokenUtil.generate(customUserDetails, customerLoginRequest.isRememberMe());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpServletRequest request = securityContextUtil.getCurrentHttpRequest().get();
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return new LoginResponse(currentUser.getUsername(), token, currentUser.getRole());

        } catch (BadCredentialsException badCredentialsException) {

            throw new IncorrectEntryException("Kullanıcı adını veya şifreyi yanlış girdiniz.");

        } catch (DisabledException disabledException) {
            throw new UserDisabledException("Hesabınızın Yönetici tarafından Onaylanması bekleniyor.");
        }
    }

    @Override
        public Account findByUserName(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Kullanıcı adı bulunamadı, lütfen kontrol ediniz ."));
    }

    @Override
    public Boolean existByUsername(String s) {
        return accountRepository.existsByUsername(s);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);

    }

    @Override
    public void activeUser(Long userId) {
        Account account=findById(userId);
        account.setIsActive(true);
        accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Kullanıcı bulunamadı."));
    }


}
