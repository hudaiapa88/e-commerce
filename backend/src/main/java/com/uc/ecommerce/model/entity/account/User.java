package com.uc.ecommerce.model.entity.account;


import com.uc.ecommerce.model.dto.account.SaveUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
@DiscriminatorValue(value = Role.Values.USER)
public class User extends Account {
    public User() {
        this.setIsActive(Boolean.FALSE);
    }

    @Column(unique = true)
    private Address address;






}
