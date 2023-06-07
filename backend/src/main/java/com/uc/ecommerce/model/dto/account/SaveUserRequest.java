package com.uc.ecommerce.model.dto.account;

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
public class SaveUserRequest {
    @NotEmpty(message = "Ad alanı boş olamaz.")
    @Size(min = 2,max = 40,message = "Ad en az 2 en fazla 40 karakter girebilirsiniz.")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$",message = "Lütfen geçerli bir ad giriniz.")
    private String firstName;
    @NotEmpty(message = "Soyad alanı boş olamaz.")
    @Size(min = 2, max = 40, message = "Soyad en az 2 en fazla 40 karakter girebilirsiniz.")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$", message = "Lütfen geçerli bir soyad giriniz.")
    private String lastName;
    //  @Size(min = 2,max = 40,message = "en az 2 en fazla 40 karakter girebilirsiniz.")
    private String username;
    @Size(min = 2,max = 32,message = "Şifre en az 6 en fazla 32 karakter girebilirsiniz.")
    private String password;
    @Valid
    private Phone phone;
    @Email(message = "Geçerli bir email girin.")
    private String email;
    @Valid
    private Address address;
}
