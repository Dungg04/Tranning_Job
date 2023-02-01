package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.WarehouseDTO;
import com.example.warehousemanagementapi.services.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/warehouses")
public class WarehouseController {
    @Autowired
    private IWarehouseService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getAllWarehouse(){
        return ResponseEntity.status(200).body(service.getWarehouses());
    }

    @GetMapping("/{warehouseID}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getWarehouse(@PathVariable("warehouseID")String warehouseID){
        return ResponseEntity.status(200).body(service.getWarehouse(warehouseID));
    }

    @PostMapping("/{warehouseID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createWarehouse(@PathVariable("warehouseID")String warehouseID, @RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(200).body(service.createWarehouse(warehouseID, warehouseDTO));
    }

    @PutMapping("/{warehouseID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editWarehouse(@PathVariable("warehouseID")String warehouseID, @RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(200).body(service.editWarehouse(warehouseID, warehouseDTO));
    }
}
