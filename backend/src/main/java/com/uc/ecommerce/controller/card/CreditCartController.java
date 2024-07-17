package com.uc.ecommerce.controller.card;

import com.uc.ecommerce.model.dto.card.CreditCardResponse;
import com.uc.ecommerce.model.dto.card.CreateCreditCardRequest;
import com.uc.ecommerce.model.dto.card.UpdateCreditCardRequest;
import com.uc.ecommerce.service.abstracts.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("credit-card")
@RequiredArgsConstructor
public class CreditCartController {
    private final CreditCardService creditCardService;

    @PostMapping("/user/my")
    public CreditCardResponse save(@Valid  @RequestBody CreateCreditCardRequest createCreditCardRequest){
        return creditCardService.save(createCreditCardRequest);
    }

    @PutMapping("/{id}")
    public CreditCardResponse update(@PathVariable Long id,@Valid  @RequestBody UpdateCreditCardRequest updateCreditCardRequest){
        return creditCardService.update(id,updateCreditCardRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
         creditCardService.delete(id);
    }
    @GetMapping("/{id}")
    public CreditCardResponse getById(@PathVariable Long id){
        return creditCardService.getById(id);
    }
    @GetMapping("/my")
    public List<CreditCardResponse> getMy(){
        return creditCardService.geyMy();
    }
}
