package com.uc.ecommerce.model.entity.order;

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
@Table(name="`Order`")
@Entity
public class Order extends AbstractTimestampEntity {
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines=new ArrayList<>();
    @ManyToOne
    private User user;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
