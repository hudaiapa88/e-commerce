package com.uc.ecommerce.model.entity.log;

import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.enums.AccountLogType;
import com.uc.ecommerce.model.enums.LogType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = LogType.Values.ACCOUNT)
public class AccountLog extends Log{
    @Enumerated(EnumType.STRING)
    private AccountLogType accountLogType;
    @ManyToOne
    private Account account;
}
