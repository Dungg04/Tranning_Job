package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.CustomerDTO;
import com.example.warehousemanagementapi.services.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("v1/api/customers")
public class CustomerController {

    ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.status(200).body(service.getCustomers());
    }

    @GetMapping("/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getCustomer(@PathVariable(name = "customerID") String customerID) {
        return ResponseEntity.status(200).body(service.getCustomer(customerID));
    }

    @PostMapping("/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<?> createCustomer(@PathVariable("customerID") String customerID, @RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.status(201).body(service.createCustomer(customerID, customerDTO));
    }

    @PutMapping("/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editCustomer(@PathVariable(name = "customerID") String customerID, @RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.status(201).body(service.editCustomer(customerID, customerDTO));
    }
    @DeleteMapping("/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "customerID") String customerID) {
        return ResponseEntity.status(201).body(service.deleteCustomer(customerID));
    }

    @GetMapping("/billsByCustomer/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBillsByCustomer(@PathVariable(name = "customerID")String customerID) {
        return ResponseEntity.status(200).body(service.getBillByCustomer(customerID));
    }
}
