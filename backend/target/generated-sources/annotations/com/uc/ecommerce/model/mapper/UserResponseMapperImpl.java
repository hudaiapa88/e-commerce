package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.entity.account.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-11T22:13:03+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public UserResponse entityToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( entity.getId() );
        userResponse.setCreatedDateTime( entity.getCreatedDateTime() );
        userResponse.setUpdatedDateTime( entity.getUpdatedDateTime() );
        userResponse.setAddress( entity.getAddress() );
        userResponse.setFirstName( entity.getFirstName() );
        userResponse.setLastName( entity.getLastName() );
        userResponse.setUsername( entity.getUsername() );
        userResponse.setPhone( entity.getPhone() );
        userResponse.setEmail( entity.getEmail() );
        userResponse.setIsActive( entity.getIsActive() );

        return userResponse;
    }

    @Override
    public List<UserResponse> entityListToDtoList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( entityList.size() );
        for ( User user : entityList ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }
}
