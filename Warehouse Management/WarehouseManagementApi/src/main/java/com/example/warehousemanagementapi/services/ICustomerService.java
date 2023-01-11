package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.CustomerDTO;
import com.example.warehousemanagementapi.models.Bill;
import com.example.warehousemanagementapi.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomers();
    Customer getCustomer(String customerID);
    Customer createCustomer(String customerID, CustomerDTO customerDTO);
    Customer editCustomer(String customerID, CustomerDTO customerDTO);
    List<Bill> getBillByCustomer(String customerID);
}
