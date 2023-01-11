package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.models.ReceiptDetail;

public interface IReceiptDetailService {
    ReceiptDetail getReceiptDetail(Integer receiptDetailID);
    ReceiptDetail createReceiptDetail(Integer receiptID, String productID, ReceiptDetailDTO receiptDetailDTO);
    ReceiptDetail editReceiptDetail(Integer receiptDetailID, ReceiptDetailDTO receiptDetailDTO);
}
