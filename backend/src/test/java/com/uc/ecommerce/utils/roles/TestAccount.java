package com.uc.ecommerce.utils.roles;

import com.uc.ecommerce.model.entity.account.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TestAccount<T extends Account> {
    T account;
    String token;
}
