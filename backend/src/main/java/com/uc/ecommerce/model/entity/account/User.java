package com.uc.ecommerce.model.entity.account;


import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@DiscriminatorValue(value = Role.Values.USER)
public class User extends Account {
    public User() {
        this.setIsActive(Boolean.FALSE);
    }

    @Column(unique = true)
    private Address address;


    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards = new ArrayList<>();


}
