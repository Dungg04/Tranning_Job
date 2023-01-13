package com.example.warehousemanagementapi.repositories;

import com.example.warehousemanagementapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserName(String username);

    Account findByUserNameAndPassword(String username, String password);
}
