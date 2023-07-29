package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.dto.order.OrderResponse;
import com.uc.ecommerce.model.dto.order.CreateOrderRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderRequest;
import com.uc.ecommerce.model.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    OrderResponse save(CreateOrderRequest createOrderRequest, Boolean isSaveCard);

    OrderResponse update(Long id, UpdateOrderRequest updateOrderRequest);

    OrderResponse delete(Long id);

    OrderResponse getById(Long id);

    Page<OrderResponse> getAll(Pageable pageable);

    List<OrderResponse> getAll();

    void addOrderLinePrice(Order order, BigDecimal totalPrice);

    List<OrderResponse> getByUserOrders();

    OrderResponse orderShipIt(Long id);
}
