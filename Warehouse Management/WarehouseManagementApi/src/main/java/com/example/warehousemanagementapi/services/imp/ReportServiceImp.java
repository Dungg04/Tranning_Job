package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ReceiptDetailDTO;
import com.example.warehousemanagementapi.models.Inventory;
import com.example.warehousemanagementapi.services.IReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImp implements IReportService {
    @Override
    public List<Inventory> getReports() {
        return null;
    }

    @Override
    public Inventory getReport(String reportID) {
        return null;
    }

    @Override
    public Inventory createReport(ReceiptDetailDTO reportDTO) {
        return null;
    }

    @Override
    public Inventory editReport(String reportID, ReceiptDetailDTO reportDTO) {
        return null;
    }

    //    Couldn't  find a customer with id
//    Already exist customer with id:
}
