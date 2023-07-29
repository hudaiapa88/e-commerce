package com.uc.ecommerce.model.entity.order;

import com.uc.ecommerce.model.dto.order.CreateOrderRequest;
import com.uc.ecommerce.model.embedded.Address;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.base.AbstractTimestampEntity;
import com.uc.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "`Order`")
@Entity
public class Order extends AbstractTimestampEntity {
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();
    @ManyToOne
    private User user;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public static Order create(CreateOrderRequest createOrderRequest, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(createOrderRequest.getAddress());
        order.setTotalPrice(new BigDecimal(0));
        order.setOrderStatus(OrderStatus.READING);
        return order;
    }

    public Order orderShipIt() {
        this.setOrderStatus(OrderStatus.SHIPPED);
        return this;
    }

    public Order addOrderLinePrice(BigDecimal totalPrice) {
        this.setTotalPrice(this.getTotalPrice().add(totalPrice));
        return this;
    }
}
