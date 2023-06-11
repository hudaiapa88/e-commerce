package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.card.CreditCardResponse;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardResponseMapper extends BaseMapper<CreditCardResponse, CreditCard> {
}
