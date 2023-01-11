package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.BillDTO;
import com.example.warehousemanagementapi.models.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> getBills();
    Bill getBill(int id);
    Bill createBill(String customerID, BillDTO billDTO);
    Bill editBill(int id, BillDTO billDTO);
//    String exportReport(String reportFormat);
}
