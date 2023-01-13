package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("v1/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService service;


    
}
