package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.BillDTO;
import com.example.warehousemanagementapi.services.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/api/bills")
public class BillController {
    @Autowired
    private IBillService service;

    @GetMapping
    public ResponseEntity<?> getBills() {
        return ResponseEntity.status(200).body(service.getBills());
    }

    @GetMapping("/{billID}")
    public ResponseEntity<?> getBillById(@PathVariable("billID")int billID) {
        return ResponseEntity.status(200).body(service.getBill(billID));
    }

    @PostMapping("/{customerID}")
    public ResponseEntity<?> createBill(@PathVariable("customerID")String customerID, @RequestBody BillDTO billDTO) {
        return ResponseEntity.status(201).body(service.createBill(customerID, billDTO));
    }

    @PutMapping("/{billID}")
    public ResponseEntity<?> editBill(@PathVariable("billID")int billID, @RequestBody BillDTO billDTO) {
        return ResponseEntity.status(201).body(service.editBill(billID, billDTO));
    }
}
