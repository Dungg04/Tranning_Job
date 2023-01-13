package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ManufactureDTO;
import com.example.warehousemanagementapi.services.IManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/api/manufactures")
public class ManufactureController {
    @Autowired
    private IManufactureService service;

    @GetMapping
    public ResponseEntity<?> getManufactures(){
        return ResponseEntity.status(200).body(service.getManufactures());
    }

    @GetMapping("/{manufactureID}")
    public ResponseEntity<?> getManufactureById(@PathVariable("manufactureID")String manufactureID) {
        return ResponseEntity.status(200).body(service.getManufacture(manufactureID));
    }

    @PostMapping("/{manufactureID}")
    public ResponseEntity<?> createManufacture(@PathVariable("manufactureID")String manufactureID, @RequestBody @Validated ManufactureDTO manufactureDTO) {
        return ResponseEntity.status(201).body(service.createManufacture(manufactureID, manufactureDTO));
    }

    @PutMapping("/{manufactureID}")
    public ResponseEntity<?> editManufacture(@PathVariable("manufactureID")String manufactureID, @RequestBody ManufactureDTO manufactureDTO) {
        return ResponseEntity.status(201).body(service.editManufacture(manufactureID, manufactureDTO));
    }

    @DeleteMapping("/{manufactureID")
    public ResponseEntity<?> deleteManufacture(@PathVariable("manufactureID")String manufactureID) {
        service.deleteManufacture(manufactureID);
        return ResponseEntity.status(201).body("Delete success!");
    }
}
