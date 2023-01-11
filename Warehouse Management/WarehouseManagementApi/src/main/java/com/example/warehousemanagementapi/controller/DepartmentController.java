//package com.example.warehousemanagementapi.controller;
//
//
//import com.example.warehousemanagementapi.dtos.DepartmentDTO;
//import com.example.warehousemanagementapi.services.IDepartmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/departments")
//public class DepartmentController {
//    @Autowired
//    private IDepartmentService service;
//
//    @GetMapping
//    public ResponseEntity<?> getDepartments() {
//        return ResponseEntity.status(200).body(service.getDepartments());
//    }
//
//    @GetMapping("/{departmentID}")
//    public ResponseEntity<?> getDepartmentById(@PathVariable("departmentID")String departmentID) {
//        return ResponseEntity.status(200).body(service.getDepartment(departmentID));
//    }
//
//    @PostMapping("/{departmentID}")
//    public ResponseEntity<?> createDepartment(@PathVariable("departmentID")String departmentID, @RequestBody DepartmentDTO departmentDTO) {
//        return ResponseEntity.status(201).body(service.createDepartment(departmentID, departmentDTO));
//    }
//
//    @PutMapping("/{departmentID}")
//    public ResponseEntity<?> editDepartment(@PathVariable("departmentID")String departmentID, @RequestBody DepartmentDTO departmentDTO) {
//        return ResponseEntity.status(201).body(service.editDepartment(departmentID, departmentDTO));
//    }
//}
