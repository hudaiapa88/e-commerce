package com.uc.ecommerce.repository;

import com.uc.ecommerce.model.entity.card.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    @Query(value = "select c from #{#entityName} c " +
            "left join fetch c.user u " +
            "where u.id=:userId"
    )
    List<CreditCard> findByUser_Id(@Param("userId") Long userId);
}
