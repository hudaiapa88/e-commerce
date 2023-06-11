package com.uc.ecommerce.repository;

import com.uc.ecommerce.model.entity.account.Account;
import com.uc.ecommerce.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByPhone_AreaCodeAndPhone_Number(String areaCode, String number);

    Long countByRole(Role role);

    Optional<Account> findByEmail(String email);

    boolean existsByUsername(String userName);

    @Query("FROM User u WHERE CONCAT(u.phone.areaCode, u.phone.number) = ?1")
    Optional<Account> findByFullPhone(String phone);

    Boolean existsByEmail(String email);

    Boolean existsByPhone_AreaCodeAndPhone_Number(String areaCode, String number);

    Boolean existsByPhone_AreaCodeAndPhone_NumberAndPasswordNotNull(String areaCode, String number);



}
