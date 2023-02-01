package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.filter.AuthenticationResponse;
import com.example.warehousemanagementapi.models.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAAllAccounts();
    Account getAccount(Integer id);
    Account createAccount(AccountDTO accountDTO);
    Account editAccount(Integer id, AccountDTO accountDTO);
    Boolean deleteAccount(Integer id);
    Account logIn(AccountDTO accountDTO);
//    AuthenticationResponse logIn(AccountDTO accountDTO);
}
