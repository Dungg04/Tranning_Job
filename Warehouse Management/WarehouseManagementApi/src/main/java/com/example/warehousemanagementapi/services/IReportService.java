package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.models.Inventory;

import java.util.List;

public interface IReportService {
    List<Inventory> getReports();
    Inventory getReport(String reportID);
    Inventory createReport(ReceiptDetailDTO reportDTO);
    Inventory editReport(String reportID, ReceiptDetailDTO reportDTO);


}
