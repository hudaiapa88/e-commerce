package com.uc.ecommerce.service;

import com.uc.ecommerce.model.dto.order.OrderLineResponse;
import com.uc.ecommerce.model.dto.order.SaveOrderLineRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderLineRequest;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.order.OrderLine;
import com.uc.ecommerce.model.entity.product.Product;
import com.uc.ecommerce.repository.order.OrderLineRepository;
import com.uc.ecommerce.service.imp.OrderLineService;
import com.uc.ecommerce.service.imp.OrderService;
import com.uc.ecommerce.service.imp.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<OrderLineResponse> save(Long orderId, List<SaveOrderLineRequest> saveOrderLinesRequest) {
        return null;
    }
    @Transactional
    @Override
    public List<OrderLine> save(Order order, List<SaveOrderLineRequest> saveOrderLinesRequest) {
       return saveOrderLinesRequest.stream().map((saveOrderLineRequest) -> {
           return save(order,saveOrderLineRequest);
        }).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public OrderLine save(Order order, SaveOrderLineRequest saveOrderLineRequest) {
        Product product=productService.findById(saveOrderLineRequest.getProductId());
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setProduct(product);
        orderLine.setQuantity(saveOrderLineRequest.getQuantity());
        BigDecimal totalPrice=new BigDecimal(saveOrderLineRequest.getQuantity()).multiply(product.getPrice());
        orderLine.setTotalPrice(totalPrice);
        productService.outOfStock(product,saveOrderLineRequest.getQuantity());
        orderService.addOrderLinePrice(order,totalPrice);
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
