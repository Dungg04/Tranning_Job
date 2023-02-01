package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private IAccountService service;

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.status(200).body(service.getAAllAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable("accountId")Integer accountId){
        return ResponseEntity.status(200).body(service.getAccount(accountId));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.status(200).body(service.createAccount(accountDTO));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> editAccount(@PathVariable("accountId")Integer accountId, @RequestBody AccountDTO accountDTO){
        return ResponseEntity.status(200).body(service.editAccount(accountId, accountDTO));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId")Integer accountId) {
        service.deleteAccount(accountId);
        return ResponseEntity.status(200).body("Delete success");
    }


}
