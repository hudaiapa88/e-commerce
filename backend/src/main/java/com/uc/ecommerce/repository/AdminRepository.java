package com.uc.ecommerce.repository;

import com.uc.ecommerce.model.entity.account.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);

    Admin findFirstBy();
}
