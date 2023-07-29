package com.uc.ecommerce.controller.order;

import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.model.dto.order.OrderResponse;
import com.uc.ecommerce.model.dto.order.CreateOrderRequest;
import com.uc.ecommerce.model.dto.order.UpdateOrderRequest;
import com.uc.ecommerce.service.imp.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@IsAuthenticated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse save(@Valid @RequestBody CreateOrderRequest createOrderRequest, @RequestParam Boolean isSaveCard){
        return orderService.save(createOrderRequest,isSaveCard);
    }
    @OnlyAdmin
    @PutMapping("/{id}/ship-it")
    public OrderResponse orderShipIt(@PathVariable Long id){
        return orderService.orderShipIt(id);
    }
    @GetMapping("/user/my")
    public List<OrderResponse> getByUserOrders(){
        return orderService.getByUserOrders();
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id,@RequestBody UpdateOrderRequest updateOrderRequest){
        return orderService.update(id,updateOrderRequest);
    }
    @DeleteMapping("/{id}")
    public OrderResponse delete(@PathVariable Long id){
        return orderService.delete(id);
    }
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id){
        return orderService.getById(id);
    }
    @OnlyAdmin
    @GetMapping("/pageable")
    public Page<OrderResponse> getAll(Pageable pageable){
        return orderService.getAll(pageable);
    }
    @OnlyAdmin
    @GetMapping()
    public List<OrderResponse> getAll(){
        return orderService.getAll();
    }
}
