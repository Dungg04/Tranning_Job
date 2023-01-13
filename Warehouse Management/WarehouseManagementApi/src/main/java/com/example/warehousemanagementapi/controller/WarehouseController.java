package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.WarehouseDTO;
import com.example.warehousemanagementapi.services.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/warehouses")
public class WarehouseController {
    @Autowired
    private IWarehouseService service;

    @GetMapping
    public ResponseEntity<?> getAllWarehouse(){
        return ResponseEntity.status(200).body(service.getWarehouses());
    }

    @GetMapping("/{warehouseID}")
    public ResponseEntity<?> getWarehouse(@PathVariable("warehouseID")String warehouseID){
        return ResponseEntity.status(200).body(service.getWarehouse(warehouseID));
    }

    @PostMapping("/{warehouseID}")
    public ResponseEntity<?> createWarehouse(@PathVariable("warehouseID")String warehouseID, @RequestBody WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(200).body(service.createWarehouse(warehouseID, warehouseDTO));
    }

    @PutMapping("/{warehouseID}")
    public ResponseEntity<?> editWarehouse(@PathVariable("warehouseID")String warehouseID, @RequestBody WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(200).body(service.editWarehouse(warehouseID, warehouseDTO));
    }
}
