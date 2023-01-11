package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.CustomerDTO;
import com.example.warehousemanagementapi.services.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/api/customers")
public class CustomerController {

    ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.status(200).body(service.getCustomers());
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<?> getCustomer(@PathVariable(name = "customerID") String customerID) {
        return ResponseEntity.status(200).body(service.getCustomer(customerID));
    }

    @PostMapping("/{customerID}")
    public  ResponseEntity<?> createCustomer(@PathVariable("customerID") String customerID, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(201).body(service.createCustomer(customerID, customerDTO));
    }

    @PutMapping("/{customerID}")
    public ResponseEntity<?> editCustomer(@PathVariable(name = "customerID") String customerID, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(201).body(service.editCustomer(customerID, customerDTO));
    }

    @GetMapping("/billsByCustomer/{customerID}")
    public ResponseEntity<?> getBillsByCustomer(@PathVariable(name = "customerID")String customerID) {
        return ResponseEntity.status(200).body(service.getBillByCustomer(customerID));
    }
}
