package com.example.bai1.controller;

import com.example.bai1.dto.EmployeeDTO;
import com.example.bai1.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        return  ResponseEntity.status(200).body(service.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(200).body(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(200).body(service.createEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable(name = "id")Integer id, @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.status(201).body(service.editEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id")Integer id){
        service.deleteEmployee(id);
        return ResponseEntity.status(201).body("Delete success");
    }
}
