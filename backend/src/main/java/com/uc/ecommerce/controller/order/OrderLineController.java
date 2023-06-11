package com.uc.ecommerce.controller.order;

import com.uc.ecommerce.core.security.annotation.IsAuthenticated;
import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.model.dto.order.OrderLineResponse;
import com.uc.ecommerce.model.dto.order.UpdateOrderLineRequest;
import com.uc.ecommerce.model.dto.order.SaveOrderLineRequest;
import com.uc.ecommerce.service.imp.OrderLineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-line")
@RequiredArgsConstructor
@IsAuthenticated
public class OrderLineController {
    private final OrderLineService orderLineService;

    @PostMapping("/order/{orderId}")
    public List<OrderLineResponse> save(@PathVariable Long orderId, @RequestBody List<SaveOrderLineRequest> saveOrderLinesRequest){
        return orderLineService.save(orderId,saveOrderLinesRequest);
    }

    @PutMapping("/{id}")
    public OrderLineResponse update(@PathVariable Long id, @RequestBody @Valid UpdateOrderLineRequest updateOrderRequest){
        return orderLineService.update(id,updateOrderRequest);
    }
    @DeleteMapping("/{id}")
    public OrderLineResponse delete(@PathVariable Long id){
        return orderLineService.delete(id);
    }
    @GetMapping("/{id}")
    public OrderLineResponse getById(@PathVariable Long id){
        return orderLineService.getById(id);
    }
    @GetMapping("/order/{orderId}")
    public List<OrderLineResponse> getByOrderId(@PathVariable Long orderId){
        return orderLineService.getByOrderId(orderId);
    }
    @OnlyAdmin
    @GetMapping("/pageable")
    public Page<OrderLineResponse> getAll(Pageable pageable){
        return orderLineService.getAll(pageable);
    }
    @OnlyAdmin
    @GetMapping()
    public List<OrderLineResponse> getAll(){
        return orderLineService.getAll();
    }
}
