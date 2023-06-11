package com.uc.ecommerce.repository.log;

import com.uc.ecommerce.model.entity.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Long> {
}
