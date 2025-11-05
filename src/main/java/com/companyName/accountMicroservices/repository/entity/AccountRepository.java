package com.companyName.accountMicroservices.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value="SELECT * FROM accounts WHERE FK_USER=:userCode", nativeQuery = true)
    List<Account> getAllAccountPerUser(@Param("userCode")String userCode);

    List<Account> findByFkUser(@Param("userCode")String userCode);

    List<Account> findAll();

}
