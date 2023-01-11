package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Manufacture;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import com.example.warehousemanagementapi.repositories.ManufactureRepository;
import com.example.warehousemanagementapi.repositories.ReceiptRepository;
import com.example.warehousemanagementapi.services.IReceiptService;
import com.example.warehousemanagementapi.utils.Convert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReceiptServiceImp implements IReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Override
    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public List<Receipt> getReceiptsByInputDay(Date inputDay) {
        List<Receipt> receipts = receiptRepository.findAllByInputDay(inputDay);
        return receipts;
    }

    @Override
    public Receipt getReceiptById(Integer receiptID) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if(receipt.isEmpty()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }

        return receipt.get();
    }

    @Override
    public Receipt createReceipt(String manufactureID, ReceiptDTO receiptDTO) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(manufacture.isEmpty()) {
            throw new NotFoundException("Couldn't find a manufacture with id: " + manufactureID);
        }
        Receipt receipt = new Receipt();
        Convert.fromReceiptDTOToReceipt(receiptDTO, receipt);
        receipt.setInputDay(java.time.LocalDate.now());
        receipt.setManufacture(manufacture.get());
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt editReceipt(Integer receiptID, ReceiptDTO receiptDTO) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if (receipt.isEmpty()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }
        Convert.fromReceiptDTOToReceipt(receiptDTO, receipt.get());
        return receiptRepository.save(receipt.get());
    }

    @Override
    public List<ReceiptDetail> getReceiptDetail(Integer receiptID) {
        return receiptRepository.findById(receiptID).get().getReceiptDetails();
    }
//
//    @Override
//    public String exportReport(Integer receiptID, String reportFormat) throws FileNotFoundException, JRException {
//        String path = "D:\\Training\\Report";
//        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
//        List<ReceiptDetail> receiptDetails = receipt.get().getReceiptDetails();
//
//        File file = ResourceUtils.getFile("classpath:reportReceipt.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(receiptDetails);
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createBy", "RECEIPT_DETAILS");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\receipt.html" );
//        }
//
//        if (reportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\receipt.pdf" );
//        }
//
//        return "report generated in path " + path;
//    }


    //    Couldn't  find a customer with id
//    Already exist customer with id:
}
