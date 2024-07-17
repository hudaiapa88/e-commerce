package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.core.security.SecurityContextUtil;
import com.uc.ecommerce.model.dto.order.OrderResponse;
import com.uc.ecommerce.model.dto.order.CreateOrderRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderRequest;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.card.CreditCard;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.mapper.OrderResponseMapper;
import com.uc.ecommerce.repository.order.OrderRepository;
import com.uc.ecommerce.service.events.CreditCardProducer;
import com.uc.ecommerce.service.abstracts.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final SecurityContextUtil securityContextUtil;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderEmailService orderEmailService;
    private final CreditCardService creditCardService;
    private final BankService bankService;
    private final CreditCardProducer creditCardProducer;
    @Transactional
    @Override
    public OrderResponse save(CreateOrderRequest createOrderRequest, Boolean isSaveCard) {

        User user= securityContextUtil.getUser();
        CreditCard creditCard=creditCardService.create(createOrderRequest.getCreateCreditCardRequest());
        if(isSaveCard){
            createOrderRequest.getCreateCreditCardRequest().setUserId(user.getId());
            creditCardProducer.publish(createOrderRequest.getCreateCreditCardRequest());
        }
        Order order  = orderRepository.save(Order.create(createOrderRequest,user));
        orderLineService.save(order, createOrderRequest.getOrderLines());
        order= findById(order.getId());
        bankService.pay(creditCard,order.getTotalPrice());
        orderEmailService.sendEmailToUserForNewOrder(user);
        return orderResponseMapper.entityToDto(order);
    }

    private Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Translator.toLocale("order.EntityNotFoundException")));
    }
    @Transactional
    @Override
    public OrderResponse update(Long id, UpdateOrderRequest updateOrderRequest) {
        return null;
    }

    @Override
    public OrderResponse delete(Long id) {
        return null;
    }

    @Override
    public OrderResponse getById(Long id) {
        return null;
    }

    @Override
    public Page<OrderResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderResponseMapper.entityListToDtoList(orderRepository.findAll()) ;
    }
    @Transactional
    @Override
    public void addOrderLinePrice(Order order, BigDecimal totalPrice) {
        orderRepository.save(order.addOrderLinePrice(totalPrice));
    }

    @Override
    public List<OrderResponse> getByUserOrders() {
        User user= securityContextUtil.getUser();
        return orderResponseMapper.entityListToDtoList(orderRepository.findByUser_Id(user.getId()));
    }

    @Override
    public OrderResponse orderShipIt(Long id) {
        Order order= findById(id);
        return orderResponseMapper.entityToDto( orderRepository.save(order.orderShipIt()));
    }
}
