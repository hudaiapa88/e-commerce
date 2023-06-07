package com.uc.ecommerce.model.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Embeddable
@Getter
public class Phone {
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    @Pattern(regexp = "^[0-9]*$",message = "Lütfen geçerli bir telefon numarası giriniz.")
    @Size(min = 2,max = 13,message = "Lütfen geçerli bir telefon numarası giriniz.")
    private String number;
    @Setter
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    @Pattern(regexp = "^[0-9+]*$",message = "Lütfen geçerli bir telefon numarası giriniz.")
    @Size(min = 2,max = 5)
    private String areaCode;
    @Setter
    private String countryCode;

    public void setNumber(String number){
        this.number=number.replaceAll(" ","");
    }
    @JsonIgnore
    public String getFullNumber(){

        return this.areaCode+this.number;
    }
}
