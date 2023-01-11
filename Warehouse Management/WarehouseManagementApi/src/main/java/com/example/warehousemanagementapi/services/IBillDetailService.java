package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.BillDetailDTO;
import com.example.warehousemanagementapi.models.BillDetail;

public interface IBillDetailService {
    BillDetail getBillDetail(Integer billDetailID);
    BillDetail createBillDetail(String productID, Integer billID, BillDetailDTO billDetailDTO);
    BillDetail editBillDetail(Integer billDetailID, BillDetailDTO billDetailDTO);
}
