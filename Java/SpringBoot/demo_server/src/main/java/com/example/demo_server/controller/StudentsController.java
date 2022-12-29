package com.example.demo_server.controller;

import com.example.demo_server.Repository.StudentRepository;
import com.example.demo_server.model.Student;
import com.example.demo_server.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/report/{format}")
    public String report(@PathVariable String format) throws JRException, FileNotFoundException {
        return service.exportReport(format);
    }
}
