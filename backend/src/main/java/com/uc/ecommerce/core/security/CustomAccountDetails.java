package com.uc.ecommerce.core.security;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
public class CustomAccountDetails implements UserDetails {

    private Account account;
    private String roleTag="ROLE_";

    public CustomAccountDetails(Account user) {
        this.account = user;
    }

    public Role getRole() {
        return account.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(roleTag+account.getRole().toString()));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

       return account.getIsActive();
      //  return true;
    }

}
