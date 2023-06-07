package com.uc.ecommerce.model.entity.account;

import com.uc.ecommerce.model.embedded.Phone;
import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import com.uc.ecommerce.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints = {@UniqueConstraint(name = "UK_User_Phone", columnNames = {"phone_areaCode", "phone_number"})})
public class Account extends AbstractTimestampEntity {

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    @Embedded
    private Phone phone;

    private String email;

    @Column(insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String firebaseTokenMobile;

    private String firebaseTokenWeb;

    private Boolean isActive ;


}
