package com.uc.ecommerce.controller.account;

import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.embedded.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {


    @NotNull(message = "{app.constraint.register.firstName.NotNull.message}")
    @Size(min = 2,message = "app.constraint.register.firstName.Size.message")
    private String firstName;

    @NotNull(message = "{app.constraint.register.lastName.NotNull.message}")
    @Size(min = 2,message = "{app.constraint.register.lastName.Size.message}")
    private String lastName;

    @NotNull(message = "{app.constraint.register.password.NotNull.message}")
    @Size(min = 4, max = 32,message = "{app.constraint.register.password.Size.message}")
    private String password;

    @Valid
    private Phone phone;

    @NotNull(message = "{app.constraint.register.email.NotNull.message}")
    @Email(message = "{app.constraint.register.email.Email.message}")
    private String email;

    private String customerTitle;

    @Valid
    private Address address;

}
