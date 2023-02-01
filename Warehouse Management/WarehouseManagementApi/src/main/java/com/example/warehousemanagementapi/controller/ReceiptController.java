package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import com.example.warehousemanagementapi.services.IReceiptService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/receipts")
public class ReceiptController {
    @Autowired
    private IReceiptService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllReceipt(){
        return ResponseEntity.status(200).body(service.getReceipts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReceipt(@PathVariable("id")Integer id) {
        return ResponseEntity.status(200).body(service.getReceiptById(id));
    }

    @GetMapping("/reportReceipt/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReportReceipt(@PathVariable("id")Integer id) {
        return ResponseEntity.status(200).body(service.getReportReceipt(id));
    }

    @GetMapping("/receiptsByDate/{inputDate}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReceiptsByDate(@PathVariable("inputDate")Date inputDate){
        return ResponseEntity.status(200).body(service.getReceiptsByInputDay(inputDate));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createReceipt(@PathVariable("id") String manufactureID, @RequestBody List<ReceiptDetail> receiptDetails) {
        return ResponseEntity.status(200).body(service.createReceipt(manufactureID, receiptDetails));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editReceipt(@PathVariable("id")Integer id, @RequestBody ReceiptDTO receiptDTO){
        return ResponseEntity.status(200).body(service.editReceipt(id, receiptDTO));
    }

    @GetMapping("/receiptDetail/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReceiptDetailsByReceipt(@PathVariable("id")Integer id) {
        return ResponseEntity.status(200).body(service.getReceiptDetail(id));
    }

    @GetMapping("/report/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<byte[]> report(@PathVariable("id")Integer id) throws JRException, FileNotFoundException {
        return ResponseEntity.status(200).body(service.exportReport(id));
    }
}
