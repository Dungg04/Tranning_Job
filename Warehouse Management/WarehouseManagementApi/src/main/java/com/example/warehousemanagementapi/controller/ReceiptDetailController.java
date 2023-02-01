package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.services.IReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/receiptDetails")
public class ReceiptDetailController {
    @Autowired
    private IReceiptDetailService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReceiptDetail(@PathVariable("id")Integer id){
        return ResponseEntity.status(200).body(service.getReceiptDetail(id));
    }

    @PostMapping("/{productID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createReceiptDetail(@PathVariable("productID")String productID, @RequestBody @Valid ReceiptDetailDTO receiptDetailDTO){
        return ResponseEntity.status(200).body(service.createReceiptDetail(productID, receiptDetailDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editReceiptDetail(@PathVariable("id")Integer id, @RequestBody ReceiptDetailDTO receiptDetailDTO){
        return ResponseEntity.status(200).body(service.editReceiptDetail(id, receiptDetailDTO));
    }

    @GetMapping("/byReceipt/{temp}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReceiptDetailByTemp(@PathVariable("temp")Integer temp){
        return ResponseEntity.status(200).body(service.getReceiptDetails(temp));
    }

    @GetMapping("/edit/{temp}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editReceiptDt(@PathVariable("temp")Integer temp){
        return ResponseEntity.status(200).body(service.edit(temp));
    }
}
