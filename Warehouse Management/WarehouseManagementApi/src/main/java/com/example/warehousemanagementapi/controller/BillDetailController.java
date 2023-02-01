package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.services.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("v1/api/billDetails")
public class BillDetailController {
    @Autowired
    private IBillDetailService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBillDetail(@PathVariable("id")Integer id){
        return ResponseEntity.status(200).body(service.getBillDetail(id));
    }

    @PostMapping("/{billID}/{productID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createReceiptDetail(@PathVariable("billID")Integer billID, @PathVariable("productID")String productID, @RequestBody BillDetailDTO billDetailDTO){
        return ResponseEntity.status(200).body(service.createBillDetail(productID, billID, billDetailDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editReceiptDetail(@PathVariable("id")Integer id, @RequestBody BillDetailDTO billDetailDTO){
        return ResponseEntity.status(200).body(service.editBillDetail(id, billDetailDTO));
    }
}
