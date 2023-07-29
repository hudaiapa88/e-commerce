package com.uc.ecommerce.model.entity.order;

import com.uc.ecommerce.model.dto.order.CreateOrderLineRequest;
import com.uc.ecommerce.model.entity.base.BaseEntity;
import com.uc.ecommerce.model.entity.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderLine extends BaseEntity {
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
    private Integer quantity;
    private BigDecimal totalPrice;

    public static OrderLine create(CreateOrderLineRequest createOrderLineRequest,Order order,Product product){
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setProduct(product);
        orderLine.setQuantity(createOrderLineRequest.getQuantity());
        BigDecimal totalPrice=new BigDecimal(createOrderLineRequest.getQuantity()).multiply(product.getPrice());
        orderLine.setTotalPrice(totalPrice);
        return orderLine;
    }
}
