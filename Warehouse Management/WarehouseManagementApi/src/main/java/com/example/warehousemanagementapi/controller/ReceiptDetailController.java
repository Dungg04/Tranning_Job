package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.services.IReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/receiptDetails")
public class ReceiptDetailController {
    @Autowired
    private IReceiptDetailService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getReceiptDetail(@PathVariable("id")Integer id){
        return ResponseEntity.status(200).body(service.getReceiptDetail(id));
    }

    @PostMapping("/{receiptID}/{productID}")
    public ResponseEntity<?> createReceiptDetail(@PathVariable("receiptID")Integer receiptID, @PathVariable("productID")String productID, @RequestBody ReceiptDetailDTO receiptDetailDTO){
        return ResponseEntity.status(200).body(service.createReceiptDetail(receiptID, productID, receiptDetailDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editReceiptDetail(@PathVariable("id")Integer id, @RequestBody ReceiptDetailDTO receiptDetailDTO){
        return ResponseEntity.status(200).body(service.editReceiptDetail(id, receiptDetailDTO));
    }
}
