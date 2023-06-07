package com.uc.ecommerce.controller.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateAccountRequest {
    @NotEmpty(message = "Ad alanı boş olamaz.")
    @Size(min = 2,max = 40,message = "Ad en az 2 en fazla 40 karakter girebilirsiniz.")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$",message = "Lütfen geçerli bir ad giriniz.")
    private String firstName;
    @NotEmpty(message = "Soyad alanı boş olamaz.")
    @Size(min = 2, max = 40, message = "Soyad en az 2 en fazla 40 karakter girebilirsiniz.")
    @Pattern(regexp = "^[a-zA-ZçÇğĞöÖşŞüÜİı ]*$", message = "Lütfen geçerli bir soyad giriniz.")
    private String lastName;
    @Size(min = 6, max = 32,message = "Şifrenizin uzunlu 6 ile 32 arasında olabilir." )
    private String newPassword;
    @Size(min = 6, max = 32,message = "Şifrenizin uzunlu 6 ile 32 arasında olabilir." )
    private String currentPassword;
    @Email(message = "Geçerli bir email girin.")
    private String email;
}
