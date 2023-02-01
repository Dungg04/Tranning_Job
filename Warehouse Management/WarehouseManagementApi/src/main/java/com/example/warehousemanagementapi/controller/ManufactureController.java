package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ManufactureDTO;
import com.example.warehousemanagementapi.services.IManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("v1/api/manufactures")
public class ManufactureController {
    @Autowired
    private IManufactureService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getManufactures(){
        return ResponseEntity.status(200).body(service.getManufactures());
    }

    @GetMapping("/{manufactureID}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getManufactureById(@PathVariable("manufactureID")String manufactureID) {
        return ResponseEntity.status(200).body(service.getManufacture(manufactureID));
    }

    @PostMapping("/{manufactureID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createManufacture(@PathVariable("manufactureID")String manufactureID, @RequestBody @Valid ManufactureDTO manufactureDTO) {
        return ResponseEntity.status(201).body(service.createManufacture(manufactureID, manufactureDTO));
    }

    @PutMapping("/{manufactureID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editManufacture(@PathVariable("manufactureID")String manufactureID, @RequestBody @Valid ManufactureDTO manufactureDTO) {
        return ResponseEntity.status(201).body(service.editManufacture(manufactureID, manufactureDTO));
    }

    @DeleteMapping("/{manufactureID")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteManufacture(@PathVariable("manufactureID")String manufactureID) {
        service.deleteManufacture(manufactureID);
        return ResponseEntity.status(201).body("Delete success!");
    }
}
