package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.card.CreditCardResponse;
import com.uc.ecommerce.model.entity.card.CreditCard;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-29T19:40:50+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class CreditCardResponseMapperImpl implements CreditCardResponseMapper {

    @Override
    public CreditCardResponse entityToDto(CreditCard entity) {
        if ( entity == null ) {
            return null;
        }

        CreditCardResponse creditCardResponse = new CreditCardResponse();

        creditCardResponse.setId( entity.getId() );
        creditCardResponse.setCreatedDateTime( entity.getCreatedDateTime() );
        creditCardResponse.setUpdatedDateTime( entity.getUpdatedDateTime() );
        creditCardResponse.setNo( entity.getNo() );
        creditCardResponse.setDate( entity.getDate() );
        creditCardResponse.setCvv2( entity.getCvv2() );

        return creditCardResponse;
    }

    @Override
    public List<CreditCardResponse> entityListToDtoList(List<CreditCard> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CreditCardResponse> list = new ArrayList<CreditCardResponse>( entityList.size() );
        for ( CreditCard creditCard : entityList ) {
            list.add( entityToDto( creditCard ) );
        }

        return list;
    }
}
