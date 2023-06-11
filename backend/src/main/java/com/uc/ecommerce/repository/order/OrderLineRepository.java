package com.uc.ecommerce.repository.order;

import com.uc.ecommerce.model.entity.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
