package com.uc.ecommerce.repository.order;

import com.uc.ecommerce.model.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select o from #{#entityName} o " +
            "left join fetch o.user u " +
            "where u.id=:userId"
    )
    List<Order> findByUser_Id(@Param("userId") Long userId);
}
