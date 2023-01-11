package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.CustomerDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Bill;
import com.example.warehousemanagementapi.models.Customer;
import com.example.warehousemanagementapi.repositories.CustomerRepository;
import com.example.warehousemanagementapi.services.ICustomerService;
import com.example.warehousemanagementapi.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements ICustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String customerID) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(customer.isEmpty()) {
            throw new NotFoundException("Couldn't  find a customer with id: " + customerID);
        }
        return customer.get();
    }

    @Override
    public Customer createCustomer(String customerID, CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(!customer.isEmpty()) {
            throw new BadRequestException("Already exist customer with id: " + customerID);
        }
        Customer newCustomer = new Customer();
        newCustomer.setCustomerID(customerID);
        Convert.fromCustomerDTOToCustomer(customerDTO, newCustomer);
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer editCustomer(String customerID, CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if (customer.isEmpty()) {
            throw new NotFoundException("Couldn't  find a customer with id: " + customerID);
        }
        Convert.fromCustomerDTOToCustomer(customerDTO, customer.get());
        return customerRepository.save(customer.get());
    }

    @Override
    public List<Bill> getBillByCustomer(String customerID) {
        return customerRepository.findById(customerID).get().getBills();
    }
}
