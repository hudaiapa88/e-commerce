package com.uc.ecommerce.model.dto.account;

import com.uc.ecommerce.core.validator.paramater.DuplicateEmail;
import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.embedded.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @NotEmpty(message = "{constraint.register.firstName.Empty.message}")
    @Size(min = 2,max = 40,message = "{constraint.register.firstName.Size.message}")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$",message = "{constraint.register.firstName.Pattern.message}")
    private String firstName;
    @NotEmpty(message = "{constraint.register.lastName.Empty.message}")
    @Size(min = 2, max = 40, message = "{constraint.register.lastName.Size.message}")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$", message = "{constraint.register.lastName.Pattern.message}")
    private String lastName;
    @Size(min = 2,max = 32,message = "{constraint.register.password.Size.message}")
    private String password;
    @Valid
    private Phone phone;
    @DuplicateEmail(message = "{constraint.register.email.DuplicateEmail.message}")
    @Email(message = "{constraint.register.email.Email.message}")
    private String email;
    @Valid
    private Address address;
}
