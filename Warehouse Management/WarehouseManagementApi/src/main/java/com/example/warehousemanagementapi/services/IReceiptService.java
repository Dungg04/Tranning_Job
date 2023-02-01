package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.dtos.ReportReceipt;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IReceiptService {
    List<ReportReceipt> getReceipts();
    List<Receipt> getReceiptsByInputDay(Date inputDay);
    ReportReceipt getReportReceipt(Integer receiptID);
    Receipt getReceiptById(Integer receiptID);
    Receipt createReceipt(String manufactureID, List<ReceiptDetail> receiptDetails);
    Receipt editReceipt(Integer receiptID, ReceiptDTO receiptDTO);
    List<ReceiptDetail> getReceiptDetail(Integer receiptID);
    byte[] exportReport(Integer receiptID) throws FileNotFoundException, JRException;
}
