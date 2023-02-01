package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.BillDTO;
import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.models.Bill;

import java.util.List;
import java.util.Set;

public interface IBillService {
    List<Bill> getBills();
    Bill getBill(int id);
    Bill createBill(String customerID, BillDTO billDTO, Set<BillDetailDTO> billDetailDTOS);
    Bill editBill(int id, BillDTO billDTO);
//    String exportReport(String reportFormat);
}
