package com.uc.ecommerce.repository.log;

import com.uc.ecommerce.model.entity.log.AccountLog;
import com.uc.ecommerce.model.entity.log.Log;
import com.uc.ecommerce.model.enums.AccountLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountLogRepository extends JpaRepository<AccountLog,Long> {
    @Query(value = "select al from #{#entityName} al " +
            "left join fetch al.account a " +
            "where a.id=:userId " +
            "and al.accountLogType=:accountLogType"
    )
    List<AccountLog> findByAccount_IdAndAccountLogType(@Param("userId") Long userId,@Param("accountLogType") AccountLogType accountLogType);

    void deleteByAccount_IdAndAccountLogType(Long id, AccountLogType accountLogType);
}
