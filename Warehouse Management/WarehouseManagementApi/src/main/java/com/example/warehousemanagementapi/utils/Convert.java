package com.example.warehousemanagementapi.utils;

import com.example.warehousemanagementapi.dtos.*;
import com.example.warehousemanagementapi.models.*;

public class Convert {
    public static Bill fromBillDTTOToBill(BillDTO billDTO, Bill bill) {
        bill.setHasAccount(billDTO.getHasAccount());
        bill.setDebtAccount(billDTO.getDebtAccount());
        return bill;
    }

    public static BillDetail fromBillDetailDTOToBillDetail(BillDetailDTO billDetailDTO, BillDetail billDetail) {
        billDetail.setAmount(billDetail.getAmount());
        billDetail.setPrice(billDetail.getPrice());
        return billDetail;
    }


    public static Customer fromCustomerDTOToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }

//    public static Warehouse fromWarehouseDTOToWarehouse(WarehouseDTO warehouseDTO, Warehouse warehouse) {
//        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
//        warehouse.setAddress(warehouseDTO.getAddress());
//        warehouse.setPhoneNumber(warehouseDTO.getPhoneNumber());
//        return warehouse;
//    }
//
//    public static Inventory fromReportDTOToReport(ReceiptDetailDTO reportDTO, Inventory report) {
//        report.setReportingDate(reportDTO.getReportingDate());
//        report.setMaterialCode(reportDTO.getMaterialCode());
//        report.setMaterialName(reportDTO.getMaterialName());
//        report.setUnit(reportDTO.getUnit());
//        report.setOriginalNumber(reportDTO.getOriginalNumber());
//        report.setInventoryNumber(reportDTO.getInventoryNumber());
//        report.setExportNumber(reportDTO.getExportNumber());
//        report.setFinalQuantity(reportDTO.getFinalQuantity());
//        return report;
//    }

    public static Manufacture fromManufactureDTOToManufacture(ManufactureDTO manufactureDTO, Manufacture manufacture) {
        manufacture.setManufactureName(manufactureDTO.getManufactureName());
        manufacture.setAddress(manufactureDTO.getAddress());
        manufacture.setPhoneNumber(manufactureDTO.getPhoneNumber());
        return manufacture;
    }

    public static Receipt fromReceiptDTOToReceipt(ReceiptDTO receiptDTO, Receipt receipt) {
        receipt.setIntoMoney(receiptDTO.getIntoMoney());
        receipt.setHasAccount(receiptDTO.getHasAccount());
        receipt.setDebtAccount(receiptDTO.getDebtAccount());
        return receipt;
    }

    public static ReceiptDetail fromReceiptDetailDTOToReceiptDetail(ReceiptDetailDTO receiptDetailDTO, ReceiptDetail receiptDetail) {
        receiptDetail.setAmount(receiptDetailDTO.getAmount());
        receiptDetail.setPrice(receiptDetailDTO.getPrice());
        return receiptDetail;
    }

    public static Product fromProductDTOToProduct(ProductDTO productDTO, Product product) {
        product.setProductName(productDTO.getProductName());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
        return product;
    }

//    public static Department fromDepartmentDTOToDepartment(DepartmentDTO departmentDTO, Department department) {
//        department.setDepartmentName(departmentDTO.getDepartmentName());
//        department.setPhoneNumber(department.getPhoneNumber());
//        return department;
//    }
}
