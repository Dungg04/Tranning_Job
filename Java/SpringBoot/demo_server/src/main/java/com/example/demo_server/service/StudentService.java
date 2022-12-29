package com.example.demo_server.service;

import com.example.demo_server.Repository.StudentRepository;
import com.example.demo_server.model.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Tranning\\Report";
        List<Student> students = studentRepository.findAll();

        //Load file
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Students");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\students.html" );
        }

        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\students.pdf" );
        }

        return "report generated in path " + path;
    }
}
