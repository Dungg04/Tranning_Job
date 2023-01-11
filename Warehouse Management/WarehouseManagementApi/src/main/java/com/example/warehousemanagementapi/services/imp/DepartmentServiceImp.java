//package com.example.warehousemanagementapi.services.imp;
//
//import com.example.warehousemanagementapi.dtos.DepartmentDTO;
//import com.example.warehousemanagementapi.exceptions.BadRequestException;
//import com.example.warehousemanagementapi.exceptions.NotFoundException;
//import com.example.warehousemanagementapi.services.IDepartmentService;
//import com.example.warehousemanagementapi.utils.Convert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DepartmentServiceImp implements IDepartmentService {
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Override
//    public List<Department> getDepartments() {
//        return departmentRepository.findAll();
//    }
//
//    @Override
//    public Department getDepartment(String departmentID) {
//        Optional<Department> department = departmentRepository.findById(departmentID);
//        if(department.isEmpty()) {
//            throw new NotFoundException("Couldn't  find a customer with id: " + departmentID);
//        }
//        return department.get();
//    }
//
//    @Override
//    public Department createDepartment(String departmentID, DepartmentDTO departmentDTO) {
//        Optional<Department> department = departmentRepository.findById(departmentID);
//        if(!department.isEmpty()){
//            throw new BadRequestException("Already exist customer with id: " + departmentID);
//        }
//        Department newDepartment = new Department();
//        newDepartment.setDepartmentID(departmentID);
//        Convert.fromDepartmentDTOToDepartment(departmentDTO, newDepartment);
//        return departmentRepository.save(newDepartment);
//    }
//
//    @Override
//    public Department editDepartment(String departmentID, DepartmentDTO departmentDTO) {
//        Optional<Department> department = departmentRepository.findById(departmentID);
//        if(department.isEmpty()) {
//            throw new NotFoundException("Couldn't  find a customer with id: " + departmentID);
//        }
//        Convert.fromDepartmentDTOToDepartment(departmentDTO, department.get());
//        return departmentRepository.save(department.get());
//    }
//}
