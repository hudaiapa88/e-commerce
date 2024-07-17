package com.uc.ecommerce.core.security;

import com.uc.ecommerce.controller.account.MeResponse;
import com.uc.ecommerce.core.exception.UnauthorizedTransactionException;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.entity.account.Admin;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public Account getCurrentAccount() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal() instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedTransactionException("Lütfen giriş yapın", HttpStatus.UNAUTHORIZED);
        }
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();
        return userDetails.getAccount();
    }

    public Admin getAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();
        if (userDetails.getRole() == Role.ADMIN) {
            return (Admin) userDetails.getAccount();
        } else {
            throw new UnauthorizedTransactionException("Yanlış ROL!!!!!");
        }
    }
    public User getUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();
        if (userDetails.getRole() == Role.USER) {

            return (User) userDetails.getAccount();

        } else {
            throw new UnauthorizedTransactionException("Yanlış ROL!!!!!");
        }
    }

    public Long getCurrentUserId() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();

        return userDetails.getAccount().getId();
    }



    public Role getCurrentUserRole() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();
        return userDetails.getRole();
    }


    public static MeResponse getMe() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomAccountDetails userDetails = (CustomAccountDetails) authentication.getPrincipal();
        return MeResponse.builder()
                        .id(userDetails.getAccount().getId())
                        .firstName(userDetails.getAccount().getFirstName())
                        .lastName(userDetails.getAccount().getLastName())
                        .userName(userDetails.getUsername())
                        .role(userDetails.getRole())
                        .email(userDetails.getAccount().getEmail())
                        .build();
    }

    public static MeResponse getMobile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {

            return MeResponse.builder()
                    .build();
        } else {
            CustomAccountDetails customAccountDetails = (CustomAccountDetails) authentication.getPrincipal();
            return MeResponse.builder()
                    .firstName(customAccountDetails.getAccount().getFirstName())
                    .lastName(customAccountDetails.getAccount().getLastName())
                    .userName(customAccountDetails.getUsername())
                    .role(customAccountDetails.getRole())
                    .build();
        }

    }

    public static Optional<HttpServletRequest> getCurrentHttpRequest() {

        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        SecurityContextUtil.context = context;
    }

}
