package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.order.OrderResponse;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper extends BaseMapper<OrderResponse, Order> {
}
