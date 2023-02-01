package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.models.ReceiptDetail;

import java.util.List;

public interface IReceiptDetailService {
    List<ReceiptDetail> getReceiptDetails(Integer temp);
    List<ReceiptDetail> edit(Integer temp);
    ReceiptDetail getReceiptDetail(Integer receiptDetailID);
    ReceiptDetail createReceiptDetail(String productID, ReceiptDetailDTO receiptDetailDTO);
    ReceiptDetail editReceiptDetail(Integer receiptDetailID, ReceiptDetailDTO receiptDetailDTO);
}
