package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.core.security.SecurityContextUtil;
import com.uc.ecommerce.model.dto.card.CreditCardResponse;
import com.uc.ecommerce.model.dto.card.SaveCreditCardRequest;
import com.uc.ecommerce.model.dto.card.UpdateCreditCardRequest;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.model.mapper.CreditCardResponseMapper;
import com.uc.ecommerce.repository.CreditCardRepository;
import com.uc.ecommerce.service.imp.CreditCardService;
import com.uc.ecommerce.service.imp.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardManager implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final SecurityContextUtil securityContextUtil;
    private final CreditCardResponseMapper creditCardResponseMapper;
    private final UserService userService;

    @Transactional
    @Override
    public CreditCardResponse save(SaveCreditCardRequest saveCreditCardRequest) {
        User user= userService.findById(saveCreditCardRequest.getUserId());
        CreditCard card= new CreditCard();
        card.setNo(saveCreditCardRequest.getNo());
        card.setDate(saveCreditCardRequest.getDate());
        card.setCvv2(saveCreditCardRequest.getCvv2());
        card.setUser(user);
        return creditCardResponseMapper.entityToDto(creditCardRepository.save(card));
    }
    @Override
    public CreditCard create(SaveCreditCardRequest saveCreditCardRequest) {
        User user= securityContextUtil.getUser();
        CreditCard card= new CreditCard();
        card.setNo(saveCreditCardRequest.getNo());
        card.setDate(saveCreditCardRequest.getDate());
        card.setCvv2(saveCreditCardRequest.getCvv2());
        card.setUser(user);
        return card;
    }
    @Transactional
    @Override
    public CreditCard save(CreditCard card) {

        return creditCardRepository.save(card);
    }
    @Transactional
    @Override
    public CreditCardResponse update(Long id, UpdateCreditCardRequest updateCreditCardRequest) {
        CreditCard card= findById(id);
        card.setNo(updateCreditCardRequest.getNo());
        card.setDate(updateCreditCardRequest.getDate());
        card.setCvv2(updateCreditCardRequest.getCvv2());
        return creditCardResponseMapper.entityToDto(creditCardRepository.save(card));
    }
    @Override
    public CreditCard findById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Translator.toLocale("card.EntityNotFoundException")));
    }

    @Transactional
    @Override
    public void delete(Long id) {
         creditCardRepository.deleteById(id);
    }

    @Override
    public CreditCardResponse getById(Long id) {
        return creditCardResponseMapper.entityToDto(findById(id)) ;
    }

    @Override
    public List<CreditCardResponse> geyMy() {
        User user= securityContextUtil.getUser();
        return creditCardResponseMapper.entityListToDtoList(creditCardRepository.findByUser_Id(user.getId()));
    }
}
