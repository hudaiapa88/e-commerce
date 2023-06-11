package com.uc.ecommerce.model.entity.account;



import com.uc.ecommerce.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = Role.Values.ADMIN)
public class Admin extends Account{

    public Admin(){
        setIsActive(Boolean.TRUE);
    }


}
