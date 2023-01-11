package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.services.IReceiptService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;

@RestController
@RequestMapping("v1/api")
public class ReceiptController {
    @Autowired
    private IReceiptService service;

    @GetMapping("/receipts")
    public ResponseEntity<?> getAllReceipt(){
        return ResponseEntity.status(200).body(service.getReceipts());
    }

    @GetMapping("/receipts/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable("id")Integer id) {
        return ResponseEntity.status(200).body(service.getReceiptById(id));
    }

    @GetMapping("/receiptsByDate/{inputDate}")
    public ResponseEntity<?> getReceiptsByDate(@PathVariable("inputDate")Date inputDate){
        return ResponseEntity.status(200).body(service.getReceiptsByInputDay(inputDate));
    }

    @PostMapping("/receipts/{id}")
    public ResponseEntity<?> createReceipt(@PathVariable("id") String manufactureID, @RequestBody ReceiptDTO receiptDTO) {
        return ResponseEntity.status(200).body(service.createReceipt(manufactureID, receiptDTO));
    }

    @PutMapping("/receipts/{id}")
    public ResponseEntity<?> editReceipt(@PathVariable("id")Integer id, @RequestBody ReceiptDTO receiptDTO){
        return ResponseEntity.status(200).body(service.editReceipt(id, receiptDTO));
    }

    @GetMapping("/receiptDetail/{id}")
    public ResponseEntity<?> getReceiptDetailsByReceipt(@PathVariable("id")Integer id) {
        return ResponseEntity.status(200).body(service.getReceiptDetail(id));
    }
//
//    @GetMapping("/report/{id}/{format}")
//    public String report(@PathVariable("id")Integer id, @PathVariable String format) throws JRException, FileNotFoundException {
//        return service.exportReport(id, format);
//    }
}
