package com.example.bai1.services.imp;

import com.example.bai1.dto.EmployeeDTO;
import com.example.bai1.exceptionn.NotFoundException;
import com.example.bai1.exceptionn.ResourceNotFoundException;
import com.example.bai1.models.Employee;
import com.example.bai1.repositories.EmployeeRepository;
import com.example.bai1.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NotFoundException("Employee not found for this id " + id);
        }
        return employee.get();
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee e = new Employee();
        e.setFirstName(employeeDTO.getFirstName());
        e.setLastName(employeeDTO.getLastName());
        e.setEmailId(employeeDTO.getEmailId());

        return employeeRepository.save(e);
    }

    @Override
    public Employee editEmployee(Integer id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NotFoundException("Employee not found for this id " + id);
        }
        employee.get().setFirstName(employeeDTO.getFirstName());
        employee.get().setLastName(employeeDTO.getLastName());
        employee.get().setEmailId(employeeDTO.getEmailId());
        return employeeRepository.save(employee.get());
    }

    @Override
    public Boolean deleteEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NotFoundException("Employee not found for this id " + id);
        }
        employeeRepository.delete(employee.get());
        return true;
    }
}
