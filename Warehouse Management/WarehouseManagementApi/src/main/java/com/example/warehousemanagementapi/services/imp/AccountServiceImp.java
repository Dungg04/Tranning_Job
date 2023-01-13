package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Account;
import com.example.warehousemanagementapi.repositories.AccountRepository;
import com.example.warehousemanagementapi.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> accounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        return account.get();
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserName(accountDTO.getUsername());
        if (account != null) {
            throw new BadRequestException("Already exist account with username: " + accountDTO.getUsername());
        }
        Account newAccount = new Account();
        newAccount.setUserName(accountDTO.getUsername());
        newAccount.setPassword(accountDTO.getPassword());
        return accountRepository.save(newAccount);
    }

    @Override
    public Account editAccount(Integer id, AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        if (account != null) {
            throw new BadRequestException("Already exist account with username: " + accountDTO.getUsername());
        }
        account.get().setUserName(accountDTO.getUsername());
        account.get().setPassword(accountDTO.getPassword());
        return accountRepository.save(account.get());
    }

    @Override
    public Boolean deleteAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        accountRepository.delete(account.get());
        return true;
    }

    @Override
    public Boolean logIn(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserNameAndPassword(accountDTO.getUsername(), accountDTO.getPassword());
        if(account == null) {
            throw new NotFoundException("Username or Password is incorrect");
        }
        return true;
    }
}
