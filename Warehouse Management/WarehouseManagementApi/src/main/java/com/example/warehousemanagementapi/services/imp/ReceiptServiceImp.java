package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.ReceiptDTO;
import com.example.warehousemanagementapi.dtos.ReportReceipt;
import com.example.warehousemanagementapi.dtos.ReportReceiptDTO;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.models.Manufacture;
import com.example.warehousemanagementapi.models.Receipt;
import com.example.warehousemanagementapi.models.ReceiptDetail;
import com.example.warehousemanagementapi.repositories.ManufactureRepository;
import com.example.warehousemanagementapi.repositories.ReceiptDetailRepository;
import com.example.warehousemanagementapi.repositories.ReceiptRepository;
import com.example.warehousemanagementapi.services.IReceiptService;
import com.example.warehousemanagementapi.utils.Convert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReceiptServiceImp implements IReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;

    @Override
    public List<ReportReceipt> getReceipts() {
        List<Receipt> receipts = receiptRepository.findAll();
        List<ReportReceipt> reportReceipts  = new ArrayList<>();
        for(Receipt item: receipts) {
            ReportReceipt reportReceipt = new ReportReceipt();
            reportReceipt.setReceiptID(item.getReceiptID());
            reportReceipt.setManufactureID(item.getManufacture().getManufactureID());
            reportReceipt.setManufactureName(item.getManufacture().getManufactureName());

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formatDate = item.getInputDay().format(dateTimeFormatter);

            reportReceipt.setInputDay(formatDate);
            reportReceipt.setIntoMoney(item.getIntoMoney());
            reportReceipts.add(reportReceipt);
        }
        return reportReceipts;
    }

    @Override
    public List<Receipt> getReceiptsByInputDay(Date inputDay) {
        List<Receipt> receipts = receiptRepository.findAllByInputDay(inputDay);
        return receipts;
    }

    @Override
    public ReportReceipt getReportReceipt(Integer receiptID) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if(!receipt.isPresent()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }
        ReportReceipt reportReceipt = new ReportReceipt();
        reportReceipt.setReceiptID(receipt.get().getReceiptID());
        reportReceipt.setManufactureID(receipt.get().getManufacture().getManufactureID());
        reportReceipt.setManufactureName(receipt.get().getManufacture().getManufactureName());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDate = receipt.get().getInputDay().format(dateTimeFormatter);

        reportReceipt.setInputDay(formatDate);
        reportReceipt.setIntoMoney(receipt.get().getIntoMoney());
        return reportReceipt;
    }

    @Override
    public Receipt getReceiptById(Integer receiptID) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if(!receipt.isPresent()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }

        return receipt.get();
    }

    @Override
    @Transactional
    public Receipt createReceipt(String manufactureID, List<ReceiptDetail> receiptDetails) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(manufactureID);
        if(!manufacture.isPresent()) {
            throw new NotFoundException("Couldn't find a manufacture with id: " + manufactureID);
        }
        Receipt receipt = new Receipt();
        receipt.setInputDay(java.time.LocalDate.now());
        receipt.setManufacture(manufacture.get());
        receipt.setReceiptDetails(receiptDetails);
        double total = 0;
        for (ReceiptDetail item: receiptDetails) {
            total += item.getAmount()*item.getPrice();
        }
        receipt.setIntoMoney(total);
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt editReceipt(Integer receiptID, ReceiptDTO receiptDTO) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        if (!receipt.isPresent()) {
            throw new NotFoundException("Couldn't find a receipt with id: " + receiptID);
        }
        Convert.fromReceiptDTOToReceipt(receiptDTO, receipt.get());
        return receiptRepository.save(receipt.get());
    }

    @Override
    public List<ReceiptDetail> getReceiptDetail(Integer receiptID) {
        return receiptRepository.findById(receiptID).get().getReceiptDetails();
    }

    @Override
    public byte[] exportReport(Integer receiptID) throws JRException {
        Optional<Receipt> receipt = receiptRepository.findById(receiptID);
        List<ReceiptDetail> receiptDetails = receipt.get().getReceiptDetails();
        List<ReportReceiptDTO> reportReceiptDTOS = new ArrayList<>();

        for (int i = 0; i<receiptDetails.size(); i++) {
            ReportReceiptDTO receiptDTO = new ReportReceiptDTO();
            receiptDTO.setIndex(i+1);
            receiptDTO.setProductId(receiptDetails.get(i).getProduct().getProductID());
            receiptDTO.setProductName(receiptDetails.get(i).getProduct().getProductName());
            receiptDTO.setAmount(receiptDetails.get(i).getAmount());
            receiptDTO.setPrice(receiptDetails.get(i).getPrice());
            receiptDTO.setTotal(receiptDetails.get(i).getAmount()*receiptDetails.get(i).getPrice());
            reportReceiptDTOS.add(receiptDTO);
        }

        double intoMoney = 0;
        for (ReportReceiptDTO item:reportReceiptDTOS) {
            intoMoney += item.getTotal();
        }

        receipt.get().setIntoMoney(intoMoney);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDate = receipt.get().getInputDay().format(dateTimeFormatter);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("manufactureName", receipt.get().getManufacture().getManufactureName());
        parameter.put("address", receipt.get().getManufacture().getAddress());
        parameter.put("inputDay", formatDate);
        parameter.put("intoMoney", receipt.get().getIntoMoney());
            JasperReport jasperReport = getCompiledFile("reportReceipt" + ".jasper", "reportReceipt" + ".jrxml");
            jasperReport.setProperty("net.sf.jasperreports.default.pdf.encoding", "Cp1250");
            return generateReportPDF(reportReceiptDTOS, parameter, jasperReport);




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


    }

    public static JasperReport getCompiledFile(String jasperPath, String jrxmlPath) throws JRException {
        File reportFile = null;
        try {
            String path = getReportLocation();
            reportFile = ResourceUtils.getFile(path + "/report/" + jasperPath);
        } catch (FileNotFoundException e) {
            for (StackTraceElement ex : e.getStackTrace()) {
            }
            e.printStackTrace();
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(reportFile.getPath()));
        } catch (FileNotFoundException e) {
        }

        return (JasperReport) JRLoader.loadObject(bufferedInputStream);
    }

    public static String getReportLocation() {
        File currentDirectory = new File(new File("").getAbsolutePath());
        return currentDirectory.getAbsolutePath();
    }

    public static byte[] generateReportPDF(List<?> dataSource, Map parameters, JasperReport jasperReport) throws JRException {
        byte[] bytes = null;
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
        bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, beanDataSource);

        return bytes;
    }

}
