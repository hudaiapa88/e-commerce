package com.uc.ecommerce.service;

import com.uc.ecommerce.model.dto.order.OrderLineResponse;
import com.uc.ecommerce.model.dto.order.CreateOrderLineRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderLineRequest;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.order.OrderLine;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.repository.order.OrderLineRepository;
import com.uc.ecommerce.service.abstracts.OrderLineService;
import com.uc.ecommerce.service.abstracts.OrderService;
import com.uc.ecommerce.service.abstracts.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderLineManager implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ProductService productService;
    @Lazy
    private final OrderService orderService;
    @Transactional
    @Override
    public List<OrderLineResponse> save(Long orderId, List<CreateOrderLineRequest> saveOrderLinesRequest) {
        return null;
    }
    @Transactional
    @Override
    public List<OrderLine> save(Order order, List<CreateOrderLineRequest> saveOrderLinesRequest) {
       return saveOrderLinesRequest.stream().map((createOrderLineRequest) -> {
           return save(order, createOrderLineRequest);
        }).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public OrderLine save(Order order, CreateOrderLineRequest createOrderLineRequest) {
        Product product=productService.findById(createOrderLineRequest.getProductId());
        OrderLine orderLine= OrderLine.create(createOrderLineRequest,order,product);
        productService.outOfStock(product, createOrderLineRequest.getQuantity());
        orderService.addOrderLinePrice(order,orderLine.getTotalPrice());
       return orderLineRepository.save(orderLine);
    }

    @Transactional
    @Override
    public OrderLineResponse update(Long id, UpdateOrderLineRequest updateOrderRequest) {
        return null;
    }
    @Transactional
    @Override
    public OrderLineResponse delete(Long id) {
        return null;
    }

    @Override
    public OrderLineResponse getById(Long id) {
        return null;
    }

    @Override
    public List<OrderLineResponse> getByOrderId(Long orderId) {
        return null;
    }

    @Override
    public Page<OrderLineResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderLineResponse> getAll() {
        return null;
    }

}
