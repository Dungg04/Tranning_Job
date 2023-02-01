package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.dtos.BillDTO;
import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.services.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("v1/api/bills")
public class BillController {
    @Autowired
    private IBillService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBills() {
        return ResponseEntity.status(200).body(service.getBills());
    }

    @GetMapping("/{billID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBillById(@PathVariable("billID")int billID) {
        return ResponseEntity.status(200).body(service.getBill(billID));
    }

    @PostMapping("/{customerID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createBill(@PathVariable("customerID")String customerID, @RequestBody BillDTO billDTO, @RequestBody Set<BillDetailDTO> billDetailDTOS) {
        return ResponseEntity.status(201).body(service.createBill(customerID, billDTO, billDetailDTOS));
    }

    @PutMapping("/{billID}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> editBill(@PathVariable("billID")int billID, @RequestBody BillDTO billDTO) {
        return ResponseEntity.status(201).body(service.editBill(billID, billDTO));
    }
}
