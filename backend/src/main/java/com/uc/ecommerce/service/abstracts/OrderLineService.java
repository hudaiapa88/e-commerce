package com.uc.ecommerce.service.abstracts;

import com.uc.ecommerce.model.dto.order.OrderLineResponse;
import com.uc.ecommerce.model.dto.order.CreateOrderLineRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderLineRequest;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.order.OrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderLineService {
     List<OrderLineResponse> save(Long orderId, List<CreateOrderLineRequest> saveOrderLinesRequest);
    List<OrderLine> save(Order order,List<CreateOrderLineRequest> saveOrderLinesRequest);

    OrderLine save(Order order, CreateOrderLineRequest createOrderLineRequest);

    OrderLineResponse update(Long id, UpdateOrderLineRequest updateOrderRequest);

    OrderLineResponse delete(Long id);

    OrderLineResponse getById(Long id);

    List<OrderLineResponse> getByOrderId(Long orderId);

    Page<OrderLineResponse> getAll(Pageable pageable);

    List<OrderLineResponse> getAll();

}
