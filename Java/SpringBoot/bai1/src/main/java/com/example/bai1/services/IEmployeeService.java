package com.example.bai1.services;

import com.example.bai1.dto.EmployeeDTO;
import com.example.bai1.models.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployee();
    Employee getEmployeeById(Integer id);
    Employee createEmployee(EmployeeDTO employeeDTO);
    Employee editEmployee(Integer id, EmployeeDTO employeeDTO);
    Boolean deleteEmployee(Integer id);
}
