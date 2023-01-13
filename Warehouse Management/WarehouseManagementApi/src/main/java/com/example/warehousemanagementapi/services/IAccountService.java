package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.models.Account;

import java.util.List;

public interface IAccountService {
    List<Account> accounts();
    Account getAccount(Integer id);
    Account createAccount(AccountDTO accountDTO);
    Account editAccount(Integer id, AccountDTO accountDTO);
    Boolean deleteAccount(Integer id);
    Boolean logIn(AccountDTO accountDTO);
}
