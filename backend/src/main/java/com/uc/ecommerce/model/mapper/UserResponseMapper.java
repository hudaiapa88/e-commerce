package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends BaseMapper<UserResponse, User> {

}
