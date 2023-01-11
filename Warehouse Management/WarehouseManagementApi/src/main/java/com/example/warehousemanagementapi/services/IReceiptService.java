package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public interface IReceiptService {
    List<Receipt> getReceipts();
    List<Receipt> getReceiptsByInputDay(Date inputDay);
    Receipt getReceiptById(Integer receiptID);
    Receipt createReceipt(String manufactureID, ReceiptDTO receiptDTO);
    Receipt editReceipt(Integer receiptID, ReceiptDTO receiptDTO);
    List<ReceiptDetail> getReceiptDetail(Integer receiptID);
//    String exportReport(Integer receiptID, String reportFormat) throws FileNotFoundException, JRException;
}
