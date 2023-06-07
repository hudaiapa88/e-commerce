package com.uc.ecommerce.controller.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PhoneRequest {
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    @Pattern(regexp = "^[0-9]*$",message = "Lütfen geçerli bir telefon numarası giriniz.")
    @Size(min = 2,max = 10,message = "Lütfen geçerli bir telefon numarası giriniz.")
    private String number;
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    @Pattern(regexp = "^[0-9+]*$",message = "Lütfen geçerli bir telefon numarası giriniz.")
    @Size(min = 2,max = 5)
    private String areaCode;
    private String countryCode;
}
