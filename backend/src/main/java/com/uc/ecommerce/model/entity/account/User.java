package com.uc.ecommerce.model.entity.account;


import com.uc.ecommerce.model.dto.account.CreateUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
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


    public static User create(CreateUserRequest createUserRequest,String password,String code) {
        User user = new User();
        user.setAddress(createUserRequest.getAddress());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setUsername(createUserRequest.getEmail());
        user.setPassword(password);
        user.setPhone(createUserRequest.getPhone());
        user.setEmail(createUserRequest.getEmail());
        user.setVerificationCode(code);
        return user;
    }

    public User update(UpdateUserRequest updateUserRequest) {
        this.setAddress(updateUserRequest.getAddress());
        this.setFirstName(updateUserRequest.getFirstName());
        this.setLastName(updateUserRequest.getLastName());
        this.setUsername(updateUserRequest.getEmail());
        this.setPhone(updateUserRequest.getPhone());
        this.setEmail(updateUserRequest.getEmail());
        return this;
    }

    public User active() {
        this.setIsActive(Boolean.TRUE);
        return this;
    }
}
